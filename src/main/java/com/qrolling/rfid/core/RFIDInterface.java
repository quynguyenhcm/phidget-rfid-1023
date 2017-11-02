package com.qrolling.rfid.core;

import com.phidget22.PhidgetException;
import com.phidget22.RFID;
import com.qrolling.rfid.services.TrackingService;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 9/25/17
 */
public interface RFIDInterface {
    void addAttachListener();

    void addDetachListener();

    void addErrorListener();

    void addTagListener();

    void addTagLostListener();

    void setTrackingService(TrackingService trackingService);

    RFID getPhidgetReader();

    String getReaderCode() throws PhidgetException;
}
