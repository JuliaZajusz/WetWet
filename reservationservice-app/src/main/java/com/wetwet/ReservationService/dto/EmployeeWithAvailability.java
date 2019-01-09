package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.database.EmployeeAvailability;

import java.sql.Date;
import java.sql.Time;

public class EmployeeWithAvailability {
        public Long id;
        public java.sql.Date date;
        public java.sql.Time startTime;
        public java.sql.Time endTime;
        public Employee employee;

        public EmployeeWithAvailability() {
        }

        public EmployeeWithAvailability(Long id, Date date, Time startTime, Time endTime, Employee employee) {
            this.id = id;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
            this.employee = employee;
        }

        public EmployeeWithAvailability(EmployeeAvailability employeeAvailability, Employee employee) {
            this.id = employeeAvailability.getId();
            this.date = employeeAvailability.getDate();
            this.startTime = employeeAvailability.getStartTime();
            this.endTime = employeeAvailability.getEndTime();
            this.employee = employee;
        }
}
