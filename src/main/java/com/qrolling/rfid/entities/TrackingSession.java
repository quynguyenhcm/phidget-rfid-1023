package com.qrolling.rfid.entities;

import com.qrolling.rfid.dto.TrackingStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Entity
@Table(name = "TRACKING_SESSION")
@NamedQueries({@NamedQuery(name = "findActiveTrackingSessionByTagNumber"
        , query = "SELECT ts from TrackingSession ts where ts.trackingObject.rfidTagNumber = :tagNumber " +
        "AND ts.trackingStatus = 'ACTIVE'"
)})
public class TrackingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRACKING_OBJECT_ID", nullable = false)
    private TrackingObject trackingObject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackingSession")
    private List<Entrance> entrances = new ArrayList<>();

    @Column(name = "START_TIME", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date startTime;

    @Column(name = "END_TIME", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRACKING_STATUS")
    private TrackingStatus trackingStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackingObject getTrackingObject() {
        return trackingObject;
    }

    public void setTrackingObject(TrackingObject trackingObject) {
        this.trackingObject = trackingObject;
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public void setEntrances(List<Entrance> employees) {
        this.entrances = employees;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }


    public static final class Builder {
        Date startTime;
        Date endTime;
        private Long id;
        private TrackingObject trackingObject;
        private List<Entrance> entrances = new ArrayList<>();
        private TrackingStatus trackingStatus;

        private Builder() {
        }

        public static Builder aTrackingSession() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTrackingObject(TrackingObject trackingObject) {
            this.trackingObject = trackingObject;
            return this;
        }

        public Builder withEntrances(List<Entrance> entrances) {
            this.entrances = entrances;
            return this;
        }

        public Builder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withTrackingStatus(TrackingStatus trackingStatus) {
            this.trackingStatus = trackingStatus;
            return this;
        }

        public TrackingSession build() {
            TrackingSession trackingSession = new TrackingSession();
            trackingSession.setId(id);
            trackingSession.setTrackingObject(trackingObject);
            trackingSession.setEntrances(entrances);
            trackingSession.setStartTime(startTime);
            trackingSession.setEndTime(endTime);
            trackingSession.setTrackingStatus(trackingStatus);
            return trackingSession;
        }
    }
}
