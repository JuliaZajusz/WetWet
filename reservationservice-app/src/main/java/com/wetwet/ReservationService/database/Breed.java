package com.wetwet.ReservationService.database;


public class Breed {

  
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
  private long speciesId;


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


  public long getSpeciesId() {
    return speciesId;
  }

  public void setSpeciesId(long speciesId) {
    this.speciesId = speciesId;
  }

}
