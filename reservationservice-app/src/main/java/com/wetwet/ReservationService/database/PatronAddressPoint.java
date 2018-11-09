package com.wetwet.ReservationService.database;


public class PatronAddressPoint {

  
    /**
     * null
     */
  private long patronId;
  
    /**
     * null
     */
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
