package com.wetwet.ReservationService.database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatronAppointment {

  @Column(name = "patron_id")
  private Long patronId;

  @Id
  @Column(name = "appointment_id")
  private Long appointmentId;

  public PatronAppointment(Long patronId, Long appointmentId) {
        this.patronId = patronId;
        this.appointmentId = appointmentId;
    }

    public PatronAppointment() {
    }


  public Long getPatronId() {
    return patronId;
  }

  public void setPatronId(Long patronId) {
    this.patronId = patronId;
  }


  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

}
