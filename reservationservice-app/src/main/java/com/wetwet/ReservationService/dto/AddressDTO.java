package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.AddressPoint;
import com.wetwet.ReservationService.database.City;
import com.wetwet.ReservationService.database.Street;

public class AddressDTO {
    private Long id;
    private String houseAppartmentNumber;
    private Street street;
    private City city;

    public AddressDTO(AddressPoint addressPoint) {
        this.id = addressPoint.getId();
        this.houseAppartmentNumber = addressPoint.getHouseAppartmentNumber();
    }

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

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
