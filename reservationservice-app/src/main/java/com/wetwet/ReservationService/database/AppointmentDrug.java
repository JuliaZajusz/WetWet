package com.wetwet.ReservationService.database;


import javax.persistence.Column;

public class AppointmentDrug {

  @Column(name = "appointment_id")
  private long appointmentId;

  @Column(name = "drug_id")
  private long drugId;


  public long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(long appointmentId) {
    this.appointmentId = appointmentId;
  }


  public long getDrugId() {
    return drugId;
  }

  public void setDrugId(long drugId) {
    this.drugId = drugId;
  }

}
