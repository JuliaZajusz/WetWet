package com.wetwet.ReservationService.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credentials {


  @Id
  @Column(name = "login")
  private String login;

  @Column(name = "password_hash")
  private String passwordHash;

  @Column(name = "employee_id")
  private Long employeeId;

  public Credentials(String login, String passwordHash, Long employeeId) {
    this.login = login;
    this.passwordHash = passwordHash;
    this.employeeId = employeeId;
  }

  public Credentials() {
  }


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


  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

}
