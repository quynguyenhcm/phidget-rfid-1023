package com.qrolling.rfid.core;

import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.services.TrackingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 11/1/17
 */
public class BaseReader {

    private TrackingService trackingService;

    private String readerCode;

    static Logger logger = Logger.getLogger(BaseReader.class);

    protected void exitZone(String tag) {
        trackingService.exitZone(tag, readerCode);
        System.out.println("Tag " + tag + " has exited zone " + readerCode);
        logger.info("LOGERRRRRRRR   Tag " + tag + " has exited zone " + readerCode);
        notifyZoneChange();
    }


    protected void enteringZone(String tagNumber) {
        System.out.println("Tag read: " + tagNumber);
        trackingService.startTracking(tagNumber, readerCode);
        Entrance entrance = trackingService.addEntrance(tagNumber, readerCode);
        if (entrance != null) {
            System.out.println("Tag " + tagNumber + " is entering zone " + readerCode);
        }
        notifyZoneChange();
    }

    protected void notifyZoneChange() {
        List<TrackingObject> trackingObjects = trackingService.findObjectsInZone(readerCode);
        if (!trackingObjects.isEmpty()) {
            System.out.println("The object(s) below is now in zone " + readerCode);
            for (TrackingObject trackingObject : trackingObjects) {
                System.out.println("Object code" + trackingObject.getId() + ". Object name: " + trackingObject.getDescription());
            }
        } else {
            System.out.println("No object is currently in zone " + readerCode);
        }
    }

    @Autowired
    public void setTrackingService(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    protected TrackingService getTrackingService() {
        return trackingService;
    }

    public String getReaderCode() {
        return readerCode;
    }

    public void setReaderCode(String readerCode) {
        this.readerCode = readerCode;
    }
}
