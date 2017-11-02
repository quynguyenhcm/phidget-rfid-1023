package com.qrolling.rfid.core;

import com.phidget22.*;
import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.TrackingSession;
import com.qrolling.rfid.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 11/1/17
 */
@Component
public class PhidgetReaderImpl implements RFIDInterface {
    private RFID phidgetReader;

    private int id;

    private TrackingService trackingService;

    private String readerCode;


    public PhidgetReaderImpl() throws PhidgetException {
        initiateReader();
    }

    private void initiateReaderCode() {
        try {
            readerCode = getReaderCode();
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }

    private void initiateReader() throws PhidgetException {
        com.phidget22.Log.enable(LogLevel.INFO, null);
        phidgetReader = new RFID();
        initiateListener();
        initiateReaderCode();
        System.out.println("RFID Reader Id: " + readerCode + " is now up and running");
    }


    private void initiateListener() {
        addAttachListener();

        addDetachListener();

        addErrorListener();

        addTagListener();

        addTagLostListener();
    }

    @Override
    public void addTagLostListener() {
        phidgetReader.addTagLostListener(new RFIDTagLostListener() {
            @Override
            public void onTagLost(RFIDTagLostEvent e) {
                System.out.println("Tag lost: " + e.getTag());
                exitZone(e.getTag());

            }
        });
    }

    private void exitZone(String tag) {
        try {
            trackingService.exitZone(tag, this.getReaderCode());
            System.out.println("Tag " + tag + " has exited zone " + this.getReaderCode());
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        notifyZoneChange();
    }

    @Override
    public String getReaderCode() throws PhidgetException {
        return String.valueOf(phidgetReader.getDeviceSerialNumber());
    }

    @Override
    public void addTagListener() {
        phidgetReader.addTagListener(new RFIDTagListener() {
            @Override
            public void onTag(RFIDTagEvent e) {

                System.out.println("Tag read: " + e.getTag());
                enteringZone(e.getTag());
            }
        });
    }

    private void enteringZone(String tagNumber) {
        System.out.println("Tag read: " + tagNumber);

        TrackingSession trackingSession = trackingService.findActiveTrackingSessionByTagNumber(tagNumber);

        trackingService.startTracking(tagNumber, readerCode);
        Entrance entrance = trackingService.addEntrance(tagNumber, readerCode);
        if (entrance != null) {
            System.out.println("Tag " + tagNumber + " is entering zone " + readerCode);
        }
        notifyZoneChange();
    }

    private void notifyZoneChange() {
        List<TrackingObject> trackingObjects = trackingService.findObjectsInZone(readerCode);
        if (!trackingObjects.isEmpty()) {
            for (TrackingObject trackingObject : trackingObjects) {
                System.out.println(trackingObject.getId() + " is in zone " + readerCode);
            }
        }
    }

    @Override
    public void addErrorListener() {
        phidgetReader.addErrorListener(new ErrorListener() {
            @Override
            public void onError(ErrorEvent ee) {
                System.out.println("Error: " + ee.getDescription());
            }
        });
    }

    @Override
    public void addDetachListener() {
        phidgetReader.addDetachListener(new DetachListener() {
            @Override
            public void onDetach(DetachEvent de) {
                RFID phid = (RFID) de.getSource();
                try {
                    if (phid.getDeviceClass() != DeviceClass.VINT) {
                        System.out.println("phidgetReader " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " detached");
                    } else {
                        System.out.println("phidgetReader " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " detached");
                    }
                } catch (PhidgetException ex) {
                    System.out.println(ex.getDescription());
                }
            }
        });
    }

    @Override
    public void addAttachListener() {
        phidgetReader.addAttachListener(new AttachListener() {
            @Override
            public void onAttach(AttachEvent ae) {
                RFID phid = (RFID) ae.getSource();
                try {
                    if (phid.getDeviceClass() != DeviceClass.VINT) {
                        System.out.println("phidgetReader " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
                    } else {
                        System.out.println("phidgetReader " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
                    }
                } catch (PhidgetException ex) {
                    System.out.println(ex.getDescription());
                }
            }
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public RFID getPhidgetReader() {
        return phidgetReader;
    }

    public void setPhidgetReader(RFID phidgetReader) {
        this.phidgetReader = phidgetReader;
    }

    @Override
    @Autowired
    public void setTrackingService(TrackingService trackingService) {
        this.trackingService = trackingService;
    }
}
