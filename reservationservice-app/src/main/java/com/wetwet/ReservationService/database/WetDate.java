package com.wetwet.ReservationService.database;

import java.sql.Date;
import java.sql.Time;

public class WetDate {

    public java.sql.Date date;
    public java.sql.Time startTime;
    public java.sql.Time endTime;

    public WetDate(Date date, Time startTime, Time endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WetDate(ConsultingRoomInaccessibility inaccessibility) {
        this.date = inaccessibility.getDate();
        this.startTime = inaccessibility.getStartTime();
        this.endTime = inaccessibility.getEndTime();
    }

    public WetDate(EmployeeAvailability availability){
        this.date = availability.getDate();
        this.startTime = availability.getStartTime();
        this.endTime = availability.getEndTime();
    }

    public WetDate(Appointment appointment) {
        this.date = appointment.getDate();
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
    }

    public WetDate() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
