package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;

import java.sql.Date;
import java.sql.Time;

public class ConsultingRoomInaccessibilityWthConsultingRoom {
    public Long id;
    public java.sql.Date date;
    public java.sql.Time startTime;
    public java.sql.Time endTime;
    public ConsultingRoom consultingRoom;

    public ConsultingRoomInaccessibilityWthConsultingRoom() {
    }

    public ConsultingRoomInaccessibilityWthConsultingRoom(Long id, Date date, Time startTime, Time endTime, ConsultingRoom consultingRoom) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.consultingRoom = consultingRoom;
    }

    public ConsultingRoomInaccessibilityWthConsultingRoom(ConsultingRoomInaccessibility consultingRoomInaccessibility, ConsultingRoom consultingRoom) {
        this.id = consultingRoomInaccessibility.getId();
        this.date = consultingRoomInaccessibility.getDate();
        this.startTime = consultingRoomInaccessibility.getStartTime();
        this.endTime = consultingRoomInaccessibility.getEndTime();
        this.consultingRoom = consultingRoom;
    }
}
