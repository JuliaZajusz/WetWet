package com.wetwet.ReservationService.database;


import com.wetwet.ReservationService.dto.ConsultingRoomInaccessibilityWthConsultingRoom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsultingRoomInaccessibility {

    @Id
    private Long id;

    private java.sql.Date date;

    @Column(name = "start_time")
    private java.sql.Time startTime;

    @Column(name = "end_time")
    private java.sql.Time endTime;

    @Column(name = "consulting_room_id")
    private long consultingRoomId;

    public ConsultingRoomInaccessibility() {
    }

    public ConsultingRoomInaccessibility(ConsultingRoomInaccessibilityWthConsultingRoom c) {
        this.id = c.id;
        this.date = c.date;
        this.startTime = c.startTime;
        this.endTime = c.endTime;
        this.consultingRoomId = c.consultingRoom.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
