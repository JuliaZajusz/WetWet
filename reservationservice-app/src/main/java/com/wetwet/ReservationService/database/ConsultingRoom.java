package com.wetwet.ReservationService.database;


public class ConsultingRoom {


    /**
     * null
     */
    private Long id;

    /**
     * null
     */
  private String roomNumber;

    /**
     * null
     */
  private String description;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
