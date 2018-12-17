package com.wetwet.ReservationService.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Breed implements Serializable{


    /**
     * null
     */
  private @Id @GeneratedValue long id;

    /**
     * null
     */
  private String name;

    @Column(name = "species_id")
  private long speciesId;

    public Breed() {
    }

    public Breed(String name, long speciesId) {
        this.name = name;
        this.speciesId = speciesId;
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


  public long getSpeciesId() {
    return speciesId;
  }

  public void setSpeciesId(long speciesId) {
    this.speciesId = speciesId;
  }

}
