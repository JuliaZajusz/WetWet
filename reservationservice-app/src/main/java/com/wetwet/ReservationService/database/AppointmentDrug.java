package com.wetwet.ReservationService.database;


public class AppointmentDrug {

  
    /**
     * null
     */
  private long appointmentId;
  
    /**
     * null
     */
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
