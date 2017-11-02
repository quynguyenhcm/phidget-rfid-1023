package com.qrolling.rfid.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Entity
@Table(name = "VISITOR")
@NamedQueries({@NamedQuery(name = "Visitor.find", query = "SELECT v from Visitor v where v.firstName like :name "
        + "or v.lastName like :name")})
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMERGENCY_NUMBER")
    private String emergencyNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackingObject")
    private List<TrackingSession> trackingSessions = new ArrayList<>();

    public Visitor() {

    }

    public Visitor(String firstName, String lastName, String phone, String emergencyNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.emergencyNumber = emergencyNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
