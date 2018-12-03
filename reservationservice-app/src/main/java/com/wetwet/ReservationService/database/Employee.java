package com.wetwet.ReservationService.database;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;


  @Column(name = "user_name")
  private String userName;


  @Column(name = "first_name")
  private String firstName;

  /**
   * null
   */
  @Column(name = "last_name")
  private String lastName;

  /**
   * null
   */
  @Column(name = "position_id")
  private Long positionId;

  public Employee(Long id, String firstName, String lastName, Long positionId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.positionId = positionId;
  }

  public Employee() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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


  public Long getPositionId() {
    return positionId;
  }

  public void setPositionId(Long positionId) {
    this.positionId = positionId;
  }

}
