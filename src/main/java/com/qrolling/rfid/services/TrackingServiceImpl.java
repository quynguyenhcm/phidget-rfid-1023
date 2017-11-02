package com.qrolling.rfid.services;

import com.qrolling.rfid.dao.TrackingDao;
import com.qrolling.rfid.dao.ZoneDao;
import com.qrolling.rfid.dto.TrackingObjectDTO;
import com.qrolling.rfid.dto.TrackingStatus;
import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.TrackingSession;
import com.qrolling.rfid.entities.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private TrackingDao trackingDao;

    @Autowired
    private ZoneDao zoneDao;

    @Override
    @Transactional
    public void startNewSession(TrackingObjectDTO trackingObjectDTO) {
        TrackingObject trackingObject = TrackingObject.Builder.aTrackingObject()
                .withTrackingStatus(TrackingStatus.ACTIVE)
                .withRfidTagNumber(trackingObjectDTO.getRfidTagNumber())
                .build();
        trackingDao.startNewSession(trackingObject);
    }

    @Override
    @Transactional
    public void startTracking(String tagNumber, String zoneCode) {
        TrackingSession trackingSession = trackingDao.findActiveTrackingSessionByTagNumber(tagNumber);
        if (trackingSession == null) {
            trackingSession = startNewSession(tagNumber);
        }
    }

    @Override
    @Transactional
    public Entrance addEntrance(String tagId, String readerCode) {
        return trackingDao.addEntrance(tagId, readerCode);
    }
    
    @Override
    @Transactional
    public void exitZone(String tagId, String readerCode) {
        trackingDao.exitZone(tagId, readerCode);
    }

    private TrackingSession startNewSession(String tagNumber) {
        TrackingObject trackingObject = TrackingObject.Builder.aTrackingObject()
                .withTrackingStatus(TrackingStatus.ACTIVE)
                .withRfidTagNumber(tagNumber)
                .build();
        return trackingDao.startNewSession(trackingObject);
    }

    @Override
    public void addEntrance(TrackingObject trackingObjectDTO, Zone zone) {

    }

    @Override
    public List<Entrance> getEntrances(TrackingObject trackingObject) {
        return null;
    }

    @Override
    public Entrance getActiveZone(TrackingObject trackingObject) {
        return null;
    }

    @Override
    public TrackingSession findActiveTrackingSessionByTagNumber(String tagNumber) {
        return trackingDao.findActiveTrackingSessionByTagNumber(tagNumber);
    }


    @Override
    public List<TrackingObject> findObjectsInZone(String readerCode) {
        return zoneDao.findObjectsInZone(readerCode);
    }
}
