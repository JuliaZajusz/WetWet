package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PatientPatronPrimaryKey.class)
public class PatientPatron {

  @Id
  @Column(name = "patron_id")
  private Long patronId;

  @Id
  @Column(name = "patient_id")
  private Long patientId;

  public PatientPatron() {
  }

  public PatientPatron(Long patronId, Long patientId) {
    this.patronId = patronId;
    this.patientId = patientId;
  }


  public Long getPatronId() {
    return patronId;
  }

  public void setPatronId(Long patronId) {
    this.patronId = patronId;
  }


  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

}
