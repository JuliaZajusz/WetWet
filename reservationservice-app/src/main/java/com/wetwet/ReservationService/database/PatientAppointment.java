package com.wetwet.ReservationService.database;


public class PatientAppointment {

  
    /**
     * null
     */
  private long patientId;
  
    /**
     * null
     */
  private long appointmentId;


  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }


  public long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(long appointmentId) {
    this.appointmentId = appointmentId;
  }

}
