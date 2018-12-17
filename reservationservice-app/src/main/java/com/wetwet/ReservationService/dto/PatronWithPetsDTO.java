package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.database.Patron;

import java.util.List;

public class PatronWithPetsDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private List<Patient> pets;

    private List<AddressDTO> addresses;

    public PatronWithPetsDTO() {
    }

    public PatronWithPetsDTO(Long id, String firstName, String lastName, String phone, String email, List<Patient> pets, List<AddressDTO> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.pets = pets;
        this.addresses = addresses;
    }

    public PatronWithPetsDTO(Patron patron) {
        this.id = patron.getId();
        this.firstName = patron.getFirstName();
        this.lastName = patron.getLastName();
        this.phone = patron.getPhone();
        this.email = patron.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Patient> getPets() {
        return pets;
    }

    public void setPets(List<Patient> pets) {
        this.pets = pets;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
