package com.wetwet.ReservationService.database;


public class EmployeeAppointment {

  
    /**
     * null
     */
  private long appointmentId;
  
    /**
     * null
     */
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
