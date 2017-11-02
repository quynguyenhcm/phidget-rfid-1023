package com.qrolling.rfid.dao;

import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.TrackingSession;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public interface TrackingDao {
    /**
     * Start new tracking session for the specified trackingObject
     *
     * @param trackingObject
     */
    TrackingSession startNewSession(TrackingObject trackingObject);

    /**
     * Add track log when the object enters a specific zone
     *
     * @param tagId
     * @param readerCode
     */
    Entrance addEntrance(String tagId, String readerCode);

    /**
     * Add track log when the object enters a specific zone
     *
     * @param tagId
     * @param readerCode
     */
    void exitZone(String tagId, String readerCode);

    /**
     * Get all zones that the object has visited during a session
     *
     * @return
     */
    List<Entrance> getEntrances(TrackingObject trackingObject);

    /**
     * Return active zone that the tracking object is now currently in
     *
     * @param trackingObject
     * @return
     */
    Entrance getActiveZone(TrackingObject trackingObject);

    /**
     * Find active tracking session by RFID Tag number
     *
     * @param tagNumber
     * @return
     */
    TrackingSession findActiveTrackingSessionByTagNumber(String tagNumber);

}
