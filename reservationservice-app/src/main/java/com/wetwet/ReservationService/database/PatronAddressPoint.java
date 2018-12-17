package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatronAddressPoint {

  @Id
  @Column(name = "patron_id")
  private long patronId;

  @Column(name = "address_point_id")
  private long addressPointId;


  public long getPatronId() {
    return patronId;
  }

  public void setPatronId(long patronId) {
    this.patronId = patronId;
  }


  public long getAddressPointId() {
    return addressPointId;
  }

  public void setAddressPointId(long addressPointId) {
    this.addressPointId = addressPointId;
  }

}
