package com.wetwet.ReservationService.database;


public class Street {

  
    /**
     * null
     */
  private long id;
  
    /**
     * null
     */
  private String name;
  
    /**
     * null
     */
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
