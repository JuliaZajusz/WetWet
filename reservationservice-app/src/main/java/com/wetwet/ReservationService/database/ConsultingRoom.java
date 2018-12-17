package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class ConsultingRoom {

    private Long id;

    @Column(name = "room_number")
  private String roomNumber;

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
