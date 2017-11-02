package com.qrolling.rfid.dao;

import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.Zone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Repository
public class ZoneDaoImpl implements ZoneDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Zone createZone(String readerCode) {
        Zone zone = new Zone();
        zone.setReaderCode(readerCode);
        em.persist(zone);
        return zone;
    }

    @Override
    @Transactional
    public Zone findByReaderCode(String readerCode) {
        List zones = em.createNamedQuery("findZoneByReaderCode")
                .setParameter("readerCode", readerCode)
                .getResultList();
        return zones.isEmpty() ? createNewZone(readerCode) : (Zone) zones.get(0);
    }

    private Zone createNewZone(String readerCode) {
        Zone zone = new Zone();
        zone.setReaderCode(readerCode);
        zone.setCode(readerCode);
        zone.setDescription("This is zone " + readerCode);
        em.persist(zone);
        return zone;
    }

    @Override
    public List<TrackingObject> findObjectsInZone(String readerCode) {
        return em.createNamedQuery("findActiveObjectsByZone")
                .setParameter("readerCode", readerCode)
                .getResultList();
    }
}
