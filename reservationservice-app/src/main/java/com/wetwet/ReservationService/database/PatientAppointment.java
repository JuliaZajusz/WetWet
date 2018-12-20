package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientAppointment {

  @Column(name = "patient_id")
  private Long patientId;

  @Id
  @Column(name = "appointment_id")
  private Long appointmentId;

  public PatientAppointment(Long patientId, Long appointmentId) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }

    public PatientAppointment() {
    }


  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }


  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

}
