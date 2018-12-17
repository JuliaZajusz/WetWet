package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AddressPoint {

    @Id
    private Long id;

    @Column(name = "house_apartment_number")
    private String houseAppartmentNumber;

    @Column(name = "street_id")
    private Long streetId;

    @Column(name = "city_id")
    private Long cityId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getHouseAppartmentNumber() {
        return houseAppartmentNumber;
    }

    public void setHouseAppartmentNumber(String houseAppartmentNumber) {
        this.houseAppartmentNumber = houseAppartmentNumber;
    }


    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }


    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

}
