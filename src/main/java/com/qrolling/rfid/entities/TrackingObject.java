package com.qrolling.rfid.entities;

import com.qrolling.rfid.dto.TrackingStatus;

import javax.persistence.*;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/31/17
 */
@Entity
@Table(name = "TRACKING_OBJECT")
public class TrackingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "RFID_TAG_NUMBER")
    String rfidTagNumber;

    @Column(name = "DESCRIPTION")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRACKING_STATUS")
    private TrackingStatus trackingStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    public void setRfidTagNumber(String rfidTagNumber) {
        this.rfidTagNumber = rfidTagNumber;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static final class Builder {
        Long id;
        String rfidTagNumber;
        String description;
        private TrackingStatus trackingStatus;

        private Builder() {
        }

        public static Builder aTrackingObject() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withRfidTagNumber(String rfidTagNumber) {
            this.rfidTagNumber = rfidTagNumber;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withTrackingStatus(TrackingStatus trackingStatus) {
            this.trackingStatus = trackingStatus;
            return this;
        }

        public TrackingObject build() {
            TrackingObject trackingObject = new TrackingObject();
            trackingObject.setId(id);
            trackingObject.setRfidTagNumber(rfidTagNumber);
            trackingObject.setDescription(description);
            trackingObject.setTrackingStatus(trackingStatus);
            return trackingObject;
        }
    }
}
