package com.wetwet.ReservationService.database;


public class Appointment {

  
    /**
     * null
     */
  private long id;
  
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
  private long cost;
  
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
  private long addressPointId;
  
    /**
     * null
     */
  private long consultingRoomId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public void setCost(long cost) {
    this.cost = cost;
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


  public long getAddressPointId() {
    return addressPointId;
  }

  public void setAddressPointId(long addressPointId) {
    this.addressPointId = addressPointId;
  }


  public long getConsultingRoomId() {
    return consultingRoomId;
  }

  public void setConsultingRoomId(long consultingRoomId) {
    this.consultingRoomId = consultingRoomId;
  }

}
