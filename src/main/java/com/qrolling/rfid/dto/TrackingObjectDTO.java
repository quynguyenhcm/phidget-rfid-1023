package com.qrolling.rfid.dto;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/31/17
 */
public class TrackingObjectDTO {
    private int id;

    String rfidTagNumber;

    private TrackingStatus trackingStatus;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final class Builder {
        String rfidTagNumber;
        private int id;
        private TrackingStatus trackingStatus;

        private Builder() {
        }

        public static Builder aTrackingObjectDTO() {
            return new Builder();
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withRfidTagNumber(String rfidTagNumber) {
            this.rfidTagNumber = rfidTagNumber;
            return this;
        }

        public Builder withTrackingStatus(TrackingStatus trackingStatus) {
            this.trackingStatus = trackingStatus;
            return this;
        }

        public TrackingObjectDTO build() {
            TrackingObjectDTO trackingObjectDTO = new TrackingObjectDTO();
            trackingObjectDTO.setId(id);
            trackingObjectDTO.setRfidTagNumber(rfidTagNumber);
            trackingObjectDTO.setTrackingStatus(trackingStatus);
            return trackingObjectDTO;
        }
    }
}
