package com.wetwet.ReservationService.database;

import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String title;

    private String description;

    private Long cost;

    private java.sql.Date date;

    @Column(name = "start_time")
    private java.sql.Time startTime;

    @Column(name = "end_time")
    private java.sql.Time endTime;

    @Column(name = "address_point_id")
    private Long addressPointId;

    @Column(name = "consulting_room_id")
    private Long consultingRoomId;

    public Appointment(Long id, String title, String description, Long cost, Date date, Time startTime, Time endTime, Long addressPointId, Long consultingRoomId) {
        this.id = id;
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

    public Appointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress) {
        this.id = appointmentWithPatientAndAddress.id;
        this.title = appointmentWithPatientAndAddress.title;
        this.description = appointmentWithPatientAndAddress.description;
        this.cost = appointmentWithPatientAndAddress.cost;
        this.date = appointmentWithPatientAndAddress.date;
        this.startTime = appointmentWithPatientAndAddress.startTime;
        this.endTime = appointmentWithPatientAndAddress.endTime;
        this.addressPointId = appointmentWithPatientAndAddress.addressDTO.getId();
        this.consultingRoomId = appointmentWithPatientAndAddress.consultingRoomId;
    }

    public Long getId() {
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

    public Long getCost() {
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

    public void setCost(Long cost) {
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

    public void setId(Long id) {
        this.id = id;
    }

}
