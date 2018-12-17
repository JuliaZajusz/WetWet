package com.wetwet.ReservationService.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity

public class Patient implements Serializable{

    @Column(name = "ID")
  private @Id @GeneratedValue long id;

  private String name;

  private String sex;

  private String birthdate;

  private String coat;

    @Column(name = "special_characters")
    private String specialCharacters;

    @Column(name = "breed_id")
    private long breedId;

  public Patient(){

  }

  public Patient(String name, String sex, String birthdate, String coat, String specialCharacters, long breedId) {
    this.name = name;
    this.sex = sex;
    this.birthdate = birthdate;
    this.coat = coat;
    this.specialCharacters = specialCharacters;
    this.breedId = breedId;
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

  public long getBreedId() {
    return breedId;
  }

  public void setBreedId(long breedId) {
    this.breedId = breedId;
  }
}
