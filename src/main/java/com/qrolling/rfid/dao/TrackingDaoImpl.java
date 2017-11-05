package com.qrolling.rfid.dao;

import com.qrolling.rfid.dto.TrackingStatus;
import com.qrolling.rfid.entities.Entrance;
import com.qrolling.rfid.entities.TrackingObject;
import com.qrolling.rfid.entities.TrackingSession;
import com.qrolling.rfid.entities.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Repository
public class TrackingDaoImpl implements TrackingDao {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    ZoneDao zoneDao;

    @Override
    public TrackingSession startNewSession(TrackingObject trackingObject) {
        TrackingSession trackingSession = TrackingSession.Builder
                .aTrackingSession()
                .withTrackingObject(trackingObject)
                .withStartTime(new Date())
                .withTrackingStatus(TrackingStatus.ACTIVE)
                .build();
        em.persist(trackingObject);
        em.persist(trackingSession);
        return trackingSession;
    }

    @Override
    public Entrance addEntrance(String tagId, String readerCode) {
        Zone zone = zoneDao.findByReaderCode(readerCode);
        TrackingSession trackingSession = findActiveTrackingSessionByTagNumber(tagId);
        Entrance entrance = new Entrance();
        entrance.setEnteringTime(new Date());
        entrance.setTrackingSession(trackingSession);
        entrance.setZone(zone);
        entrance.setTrackingStatus(com.qrolling.rfid.entities.TrackingStatus.ACTIVE);
        em.persist(entrance);
        return entrance;
    }


    @Override
    public void exitZone(String tagId, String readerCode) {
        List entrances = em.createNamedQuery("findActiveEntranceByTagId")
                .setParameter("tagNumber", tagId)
                .getResultList();

        if (!entrances.isEmpty()) {
            Entrance activeEntrance = (Entrance) entrances.get(0);
            activeEntrance.setExitingTime(new Date());
            activeEntrance.setTrackingStatus(com.qrolling.rfid.entities.TrackingStatus.FINISHED);
            em.merge(activeEntrance);
        }
    }

    @Override
    public List<Entrance> getEntrances(TrackingObject trackingObject) {
        CriteriaQuery<Entrance> criteriaQuery = em.getCriteriaBuilder().createQuery(Entrance.class);
        Root<Entrance> root = criteriaQuery.from(Entrance.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Entrance getActiveZone(TrackingObject trackingObject) {
        return null;
    }

    @Override
    public TrackingSession findActiveTrackingSessionByTagNumber(String tagNumber) {
        List trackingSessions = em.createNamedQuery("findActiveTrackingSessionByTagNumber")
                .setParameter("tagNumber", tagNumber)
                .getResultList();
        return trackingSessions.isEmpty() ? null : (TrackingSession) trackingSessions.get(0);
    }

}
