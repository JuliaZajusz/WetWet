package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class AddressPoint {

    private Long id;

  @Column(name = "house_apartment_number")
  private String houseAppartmentNumber;

  @Column(name = "street_id")
  private long streetId;

  @Column(name = "city_id")
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
