package com.qrolling.rfid.core;

import com.phidget22.*;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 9/25/17
 */
public class RFIDReader {
    private RFID channel;

    private int id;

    public RFIDReader(int id) throws PhidgetException {
        com.phidget22.Log.enable(LogLevel.INFO, null);
        initiateReader(id);
    }

    private void initiateReader(int id) throws PhidgetException {
        System.out.println("Initiating RFID Reader Id: " + id);
        channel = new RFID();
        this.id = id;
        initiateListener();
        System.out.println("RFID Reader Id: " + id + " is now up and running");
    }


    private void initiateListener() {
        addAttachListener();

        addDetachListenr();

        addErrorListener();

        addTagListener();

        addTagLostListener();
    }

    private void addTagLostListener() {
        channel.addTagLostListener(new RFIDTagLostListener() {
            public void onTagLost(RFIDTagLostEvent e) {
                System.out.println("Tag lost: " + e.getTag());
            }
        });
    }

    private void addTagListener() {
        channel.addTagListener(new RFIDTagListener() {
            public void onTag(RFIDTagEvent e) {
                System.out.println("Tag read: " + e.getTag());
            }
        });
    }

    private void addErrorListener() {
        channel.addErrorListener(new ErrorListener() {
            public void onError(ErrorEvent ee) {
                System.out.println("Error: " + ee.getDescription());
            }
        });
    }

    private void addDetachListenr() {
        channel.addDetachListener(new DetachListener() {
            public void onDetach(DetachEvent de) {
                RFID phid = (RFID) de.getSource();
                try {
                    if (phid.getDeviceClass() != DeviceClass.VINT) {
                        System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " detached");
                    } else {
                        System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " detached");
                    }
                } catch (PhidgetException ex) {
                    System.out.println(ex.getDescription());
                }
            }
        });
    }

    private void addAttachListener() {
        channel.addAttachListener(new AttachListener() {
            public void onAttach(AttachEvent ae) {
                RFID phid = (RFID) ae.getSource();
                try {
                    if(phid.getDeviceClass() != DeviceClass.VINT){
                        System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
                    }
                    else{
                        System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
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

    public RFID getChannel() {
        return channel;
    }

    public void setChannel(RFID channel) {
        this.channel = channel;
    }
}
