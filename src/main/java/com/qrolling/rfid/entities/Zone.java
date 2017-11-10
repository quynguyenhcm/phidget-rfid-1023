package com.qrolling.rfid.entities;

import javax.persistence.*;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/31/17
 */
@Entity
@Table(name = "ZONE")
@NamedQueries({
        @NamedQuery(name = "findZoneByReaderCode"
                , query = "SELECT z from Zone z where z.readerCode = :readerCode "),
        @NamedQuery(name = "findActiveObjectsByZone"
                , query = "SELECT distinct to from TrackingObject to  " +
                "join TrackingSession t on t.trackingObject = to " +
                "join Entrance  e on t = e.trackingSession " +
                "join Zone z on e.zone = z " +
                "where z.readerCode = :readerCode " +
                "and e.trackingStatus = 'ACTIVE'")
})
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "CODE")
    String code;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "READER_CODE")
    String readerCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReaderCode() {
        return readerCode;
    }

    public void setReaderCode(String readerCode) {
        this.readerCode = readerCode;
    }
}
