package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Id;

//@Entity
public class EmployeeAppointment {


  @Id
  @Column(name = "appointment_id")
  private long appointmentId;

  @Id
  @Column(name = "employee_id")
  private long employeeId;


  public long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(long appointmentId) {
    this.appointmentId = appointmentId;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

}
