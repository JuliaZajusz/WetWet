package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class PatientPatron {

  @Column(name = "patron_id")
  private long patronId;

  @Column(name = "patient_id")
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
