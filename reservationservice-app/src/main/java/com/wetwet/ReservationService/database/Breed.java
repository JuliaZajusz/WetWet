package com.wetwet.ReservationService.database;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
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
  
    /**
     * null
     */
  @OneToOne
  @JoinColumn(name = "Species_ID")
  @JsonManagedReference
  private Species species;

  @OneToOne(mappedBy = "breed")
  @JsonBackReference
  private Patient patient;

  public Breed() {
  }

  public Breed(String name) {
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


  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Species getSpecies() {
    return species;
  }

  public void setSpecies(Species species) {
    this.species = species;
  }
}
