package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ConsultingRoomInaccessibilityPrimaryKey.class)
public class ConsultingRoomInaccessibility {

    @Id
    private java.sql.Date date;

    @Id
    @Column(name = "start_time")
    private java.sql.Time startTime;

    @Id
    @Column(name = "end_time")
    private java.sql.Time endTime;

    @Id
    @Column(name = "consulting_room_id")
    private long consultingRoomId;


    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }


    public java.sql.Time getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }


    public java.sql.Time getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }


    public long getConsultingRoomId() {
        return consultingRoomId;
    }

    public void setConsultingRoomId(long consultingRoomId) {
        this.consultingRoomId = consultingRoomId;
    }

}
