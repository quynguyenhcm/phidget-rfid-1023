package com.qrolling.rfid.core;

import com.phidget22.*;
import org.springframework.stereotype.Component;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 11/1/17
 */
@Component
public class PhidgetReaderImpl extends BaseReader implements RFIDInterface {
    private RFID phidgetReader;

    public PhidgetReaderImpl() throws PhidgetException {
        initiateReader();
    }

    private void initiateReaderCode() {
        try {
            setReaderCode(String.valueOf(phidgetReader.getDeviceSerialNumber()));
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }

    private void initiateReader() throws PhidgetException {
        com.phidget22.Log.enable(LogLevel.INFO, null);
        phidgetReader = new RFID();
        initiateListener();
        initiateReaderCode();
        System.out.println("RFID Reader Id: " + getReaderCode() + " is now up and running");
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
                System.out.println("Phidget 1023 tag lost: " + e.getTag());
                exitZone(e.getTag());
            }
        });
    }

    @Override
    public void startListening(int timeToListen) {
        try {
            System.out.println("\n\nGathering data for " + timeToListen + " seconds\n\n");
            phidgetReader.open(timeToListen);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(long timeToClose) {
        try {
            System.out.println("\n\nClosing the reader within " + timeToClose + " seconds\n\n");
            Thread.sleep(timeToClose);
            phidgetReader.close();
        } catch (PhidgetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addTagListener() {
        phidgetReader.addTagListener(new RFIDTagListener() {
            @Override
            public void onTag(RFIDTagEvent e) {
                System.out.println("Phidget 1023 tag read: " + e.getTag());
                enteringZone(e.getTag());
            }
        });
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

}
