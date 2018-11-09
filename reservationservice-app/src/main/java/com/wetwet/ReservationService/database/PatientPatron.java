package com.wetwet.ReservationService.database;


public class PatientPatron {

  
    /**
     * null
     */
  private long patronId;
  
    /**
     * null
     */
  private long patientId;


  public long getPatronId() {
    return patronId;
  }

  public void setPatronId(long patronId) {
    this.patronId = patronId;
  }


  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }

}
