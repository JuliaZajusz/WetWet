package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientAppointment {

  @Id
  @Column(name = "patient_id")
  private long patientId;

  @Column(name = "appointment_id")
  private long appointmentId;

    public PatientAppointment(long patientId, long appointmentId) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }

    public PatientAppointment() {
    }


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
