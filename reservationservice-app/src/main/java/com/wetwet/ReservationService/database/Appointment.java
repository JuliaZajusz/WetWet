package com.wetwet.ReservationService.database;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment")
public class Appointment {


    /**
     * null
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    /**
     * null
     */
    private String title;

    /**
     * null
     */
    private String description;

    /**
     * null
     */
    private Long cost;

    /**
     * null
     */
    private java.sql.Date date;

    /**
     * null
     */
    private java.sql.Time startTime;

    /**
     * null
     */

    private java.sql.Time endTime;

    /**
     * null
     */
    private Long addressPointId;

    /**
     * null
     //     */
    private Long consultingRoomId;

    public Appointment(String title, String description, long cost, Date date, Time startTime, Time endTime, Long addressPointId, Long consultingRoomId) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.addressPointId = addressPointId;
        this.consultingRoomId = consultingRoomId;
    }

    public Appointment() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCost() {
        return cost;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public java.sql.Time getStartTime() {
        return startTime;
    }

    public java.sql.Time getEndTime() {
        return endTime;
    }

    public Long getAddressPointId() {
        return addressPointId;
    }

    public Long getConsultingRoomId() {
        return consultingRoomId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }

    public void setAddressPointId(Long addressPointId) {
        this.addressPointId = addressPointId;
    }

    public void setConsultingRoomId(Long consultingRoomId) {
        this.consultingRoomId = consultingRoomId;
    }

    public void setId(long id) {
        this.id = id;
    }

}
