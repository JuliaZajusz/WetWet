package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.Patient;

public class PatientDTO {
    private long id;

    private String name;

    private String sex;

    private String birthdate;

    private String coat;

    private String specialCharacters;

    private BreedDTO breed;

    public PatientDTO(long id, String name, String sex, String birthdate, String coat, String specialCharacters, BreedDTO breed) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.coat = coat;
        this.specialCharacters = specialCharacters;
        this.breed = breed;
    }

    public PatientDTO(Patient patient, BreedDTO breed) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.sex = patient.getSex();
        this.birthdate = patient.getBirthdate();
        this.coat = patient.getCoat();
        this.specialCharacters = patient.getSpecialCharacters();
        this.breed = breed;
    }

    public PatientDTO() {
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public String getSpecialCharacters() {
        return specialCharacters;
    }

    public void setSpecialCharacters(String specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    public BreedDTO getBreed() {
        return breed;
    }

    public void setBreed(BreedDTO breedDTO) {
        this.breed = breedDTO;
    }
}
