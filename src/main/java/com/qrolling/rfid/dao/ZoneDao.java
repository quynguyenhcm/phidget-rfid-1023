package com.qrolling.rfid.dao;

import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.Zone;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public interface ZoneDao {
    Zone createZone(String readerCode);

    Zone findByReaderCode(String readerCode);

    List<TrackingObject> findObjectsInZone(String readerCode);
}
