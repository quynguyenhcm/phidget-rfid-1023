package com.qrolling.rfid.test;

import com.phidget22.PhidgetException;
import com.qrolling.rfid.core.RFIDReader;

public class RFIDExample {

    public static final void main(String args[]) throws Exception {
        final int TEST_ID = 1;
        RFIDReader reader = new RFIDReader(TEST_ID);
        try {
             /*
            * Please review the Phidget22 channel matching documentation for details on the device
            * and class architecture of Phidget22, and how channels are matched to device features.
            */

            /*
            * Specifies the serial number of the device to attach to.
            * For VINT devices, this is the hub serial number.
            *
            * The default is any device.
            */
            //ch.setDeviceSerialNumber(<YOUR DEVICE SERIAL NUMBER>);
            /*
            * For VINT devices, this specifies the port the VINT device must be plugged into.
            *
            * The default is any port.
            */
            //ch.setHubPort(0);

            /*
            * Specifies that the channel should only match a VINT hub port.
            * The only valid channel id is 0.
            *
            * The default is 0 (false), meaning VINT hub ports will never match
            */
            //ch.setIsHubPortDevice(true);

            /*
            * Specifies which channel to attach to.  It is important that the channel of
            * the device is the same class as the channel that is being opened.
            *
            * The default is any channel.
            */
            //ch.setChannel(0);

            /*
            * In order to attach to a network Phidget, the program must connect to a Phidget22 Network Server.
            * In a normal environment this can be done automatically by enabling server discovery, which
            * will cause the client to discovery and connect to available servers.
            *
            * To force the channel to only match a network Phidget, set remote to 1.
            */
            // Net.enableServerDiscovery(ServerType.DEVICE);
            // ch.setIsRemote(true);

            System.out.println("Opening and waiting 5 seconds for attachment...");
            reader.getChannel().open(5000);

            System.out.println("\n\nGathering data for 10 seconds\n\n");
            Thread.sleep(10000);

            reader.getChannel().close();
            System.out.println("\nClosed RFID");

        } catch (PhidgetException ex) {
            System.out.println(ex.getDescription());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
