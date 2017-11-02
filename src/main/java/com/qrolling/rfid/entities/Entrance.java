package com.qrolling.rfid.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Entity
@Table(name = "ENTRANCE")
@NamedQueries({@NamedQuery(name = "findActiveEntranceByTagId"
        , query = "SELECT e from Entrance e where e.trackingSession.trackingObject.rfidTagNumber = :tagNumber " +
        " AND e.trackingStatus = 'ACTIVE'"
)})
public class Entrance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRACKING_SESSION_ID")
    private TrackingSession trackingSession;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZONE_ID")
    private Zone zone;

    @Column(name = "ENTERING_TIME", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enteringTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TrackingStatus trackingStatus;

    public Entrance() {
    }

    public Entrance(TrackingSession trackingSession, Zone zone, Date enteringTime) {
        this.trackingSession = trackingSession;
        this.zone = zone;
        this.enteringTime = enteringTime;
        trackingStatus = TrackingStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackingSession getTrackingSession() {
        return trackingSession;
    }

    public void setTrackingSession(TrackingSession trackingSession) {
        this.trackingSession = trackingSession;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }
}
