package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class Street {

  private long id;

  private String name;

  @Column(name = "city_id")
  private long cityId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getCityId() {
    return cityId;
  }

  public void setCityId(long cityId) {
    this.cityId = cityId;
  }

}
