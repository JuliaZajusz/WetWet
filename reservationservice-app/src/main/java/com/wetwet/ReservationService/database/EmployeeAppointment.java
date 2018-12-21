package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeAppointment {



  @Id
  @Column(name = "appointment_id")
  private long appointmentId;

  //  @Id
  @Column(name = "employee_id")
  private long employeeId;

  public EmployeeAppointment() {
  }

  public EmployeeAppointment(long employeeId, long appointmentId) {
    this.appointmentId = appointmentId;
    this.employeeId = employeeId;
  }


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
