package com.wetwet.ReservationService.database;


public class Credentials {

  
    /**
     * null
     */
  private String login;
  
    /**
     * null
     */
  private String passwordHash;
  
    /**
     * null
     */
  private long employeeId;


  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }


  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

}
