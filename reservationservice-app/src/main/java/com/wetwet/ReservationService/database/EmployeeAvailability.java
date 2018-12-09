package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class EmployeeAvailability {


  private java.sql.Date date;

  @Column(name = "start_time")
  private java.sql.Time startTime;

  @Column(name = "end_time")
  private java.sql.Time endTime;

  @Column(name = "employee_id")
  private long employeeId;


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
