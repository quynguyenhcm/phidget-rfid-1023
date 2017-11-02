package com.qrolling.rfid.core;

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

    void startListening(int timeToListen);

    void close(long timeToClose);

    String getReaderCode();
}
