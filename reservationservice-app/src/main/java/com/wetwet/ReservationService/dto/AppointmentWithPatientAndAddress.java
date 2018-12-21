package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.Appointment;

import java.sql.Date;
import java.sql.Time;

public class AppointmentWithPatientAndAddress {
    public Long id;
    public String title;
    public String description;
    public Long cost;
    public java.sql.Date date;
    public java.sql.Time startTime;
    public java.sql.Time endTime;
    public Long consultingRoomId;
    public AddressDTO addressDTO;
    public Long patientId;
    public Long patronId;
    public Long employeeId;

    public AppointmentWithPatientAndAddress(Long id, String title, String description, Long cost, Date date, Time startTime, Time endTime, Long consultingRoomId, AddressDTO addressDTO, Long patientId, Long patronId, Long employeeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.consultingRoomId = consultingRoomId;
        this.addressDTO = addressDTO;
        this.patientId = patientId;
        this.patronId = patronId;
        this.employeeId = employeeId;
    }

    public AppointmentWithPatientAndAddress(Appointment appointment,
//                                            AddressDTO addressDTO,
                                            Long patientId, Long patronId, Long employeeId) {
        this.id = appointment.getId();
        this.title = appointment.getTitle();
        this.description = appointment.getDescription();
        this.cost = appointment.getCost();
        this.date = appointment.getDate();
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
        this.consultingRoomId = appointment.getConsultingRoomId();
//        this.addressDTO = addressDTO; //TODO
        this.patientId = patientId;
        this.patronId = patronId;
        this.employeeId = employeeId;
    }

    public AppointmentWithPatientAndAddress() {
    }
}
