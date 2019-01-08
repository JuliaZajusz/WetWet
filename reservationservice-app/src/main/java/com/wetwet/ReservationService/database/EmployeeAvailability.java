package com.wetwet.ReservationService.database;


import com.wetwet.ReservationService.dto.EmployeeWithAvailability;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
//@IdClass(EmployeeAvailabilityPrimaryKey.class)
public class EmployeeAvailability {

  @Id
  private Long id;

  private java.sql.Date date;

  @Column(name = "start_time")
  private java.sql.Time startTime;

  @Column(name = "end_time")
  private java.sql.Time endTime;

  @Column(name = "employee_id")
  private long employeeId;

  public EmployeeAvailability() {
  }
  public EmployeeAvailability(EmployeeWithAvailability e){
    this.id = e.id;
    this.date = e.date;
    this.startTime = e.startTime;
    this.endTime = e.endTime;
    this.employeeId = e.employee.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public java.sql.Time getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Time startTime) {
    this.startTime = startTime;
  }


  public java.sql.Time getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Time endTime) {
    this.endTime = endTime;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

}
