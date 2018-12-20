package com.wetwet.ReservationService.dto;

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
}
