package com.wetwet.ReservationService.database;


public class ConsultingRoomInaccessibility {

  
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
