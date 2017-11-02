package com.qrolling.rfid.services;

import com.qrolling.rfid.dto.TrackingObjectDTO;
import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.TrackingSession;
import com.qrolling.rfid.entities.Zone;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public interface TrackingService {
    /**
     * Start new tracking session for the specified trackingObject
     *
     * @param trackingObjectDTO
     */
    void startNewSession(TrackingObjectDTO trackingObjectDTO);

    /**
     * Start tracking for the specified RFID tag when it entering the zoneCode
     *
     * @param tagNumber
     * @param zoneCode
     */
    void startTracking(String tagNumber, String zoneCode);

    /**
     * Called when an tag entering specific zone which is determined by readerCode
     *
     * @param tagId
     * @param readerCode
     * @return
     */
    Entrance addEntrance(String tagId, String readerCode);

    /**
     * Called when an tag exiting a specific zone which is determined by readerCode
     *
     * @param tagId
     * @param readerCode
     * @return
     */
    void exitZone(String tagId, String readerCode);

    /**
     * Add track log when the object enters a specific zone
     *
     * @param trackingObjectDTO
     * @param zone
     */
    void addEntrance(TrackingObject trackingObjectDTO, Zone zone);

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


    /**
     * return list of trackingObject within a given zone
     *
     * @param readerCode
     * @return
     */
    List<TrackingObject> findObjectsInZone(String readerCode);
}
