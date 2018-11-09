package com.wetwet.ReservationService.database;


public class Employee {

  
    /**
     * null
     */
  private long id;
  
    /**
     * null
     */
  private String firstName;
  
    /**
     * null
     */
  private String lastName;
  
    /**
     * null
     */
  private long positionId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public long getPositionId() {
    return positionId;
  }

  public void setPositionId(long positionId) {
    this.positionId = positionId;
  }

}
