package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class EmployeeAppointment {


    @Column(name = "appointment_id")
  private long appointmentId;

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
