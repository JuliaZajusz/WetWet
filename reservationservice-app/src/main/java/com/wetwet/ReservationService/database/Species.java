package com.wetwet.ReservationService.database;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Species implements Serializable{

    /**
     * null
     */
  private @Id @GeneratedValue
    long id;
  
    /**
     * null
     */
  private String name;

  public Species() {
  }

  public Species(String name) {
    this.name = name;
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


}
