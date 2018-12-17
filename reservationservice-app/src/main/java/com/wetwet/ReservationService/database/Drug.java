package com.wetwet.ReservationService.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drug {


  @Id
  private Long id;

  /**
   * null
   */
  private String producent;

  /**
   * null
   */
  private String name;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getProducent() {
    return producent;
  }

  public void setProducent(String producent) {
    this.producent = producent;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
