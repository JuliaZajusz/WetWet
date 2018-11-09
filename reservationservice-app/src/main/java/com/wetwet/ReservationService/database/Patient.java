package com.wetwet.ReservationService.database;


public class Patient {

  
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
  private String sex;
  
    /**
     * null
     */
  private String birthdate;
  
    /**
     * null
     */
  private String coat;
  
    /**
     * null
     */
  private String specialCharacters;
  
    /**
     * null
     */
  private long breedId;


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
