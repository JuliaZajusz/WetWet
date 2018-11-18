package com.wetwet.ReservationService.dto;

public class PatientDTO {
    private long id;

    private String name;

    private String sex;

    private String birthdate;

    private String coat;

    private String specialCharacters;

    private BreedDTO breed;

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
