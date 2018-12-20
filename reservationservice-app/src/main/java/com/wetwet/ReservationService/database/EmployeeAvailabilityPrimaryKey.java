package com.wetwet.ReservationService.database;

import java.io.Serializable;

public class EmployeeAvailabilityPrimaryKey implements Serializable {

    private java.sql.Date date;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    private Long employeeId;
}
