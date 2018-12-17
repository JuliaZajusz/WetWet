package com.wetwet.ReservationService.database;


import javax.persistence.*;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Street {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  //  @JsonIgnore
  @Column(name = "city_id")
  private long cityId;

  public Street() {
  }

  public Street(Long id, String name, long cityId) {
    this.id = id;
    this.name = name;
    this.cityId = cityId;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getCityId() {
    return cityId;
  }

  public void setCityId(long cityId) {
    this.cityId = cityId;
  }

}
