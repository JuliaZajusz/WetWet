package com.wetwet.ReservationService.database;


public class AddressPoint {


    /**
     * null
     */
    private Long id;

    /**
     * null
     */
  private String houseAppartmentNumber;

    /**
     * null
     */
  private long streetId;

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


  public String getHouseAppartmentNumber() {
    return houseAppartmentNumber;
  }

  public void setHouseAppartmentNumber(String houseAppartmentNumber) {
    this.houseAppartmentNumber = houseAppartmentNumber;
  }


  public long getStreetId() {
    return streetId;
  }

  public void setStreetId(long streetId) {
    this.streetId = streetId;
  }


  public long getCityId() {
    return cityId;
  }

  public void setCityId(long cityId) {
    this.cityId = cityId;
  }

}
