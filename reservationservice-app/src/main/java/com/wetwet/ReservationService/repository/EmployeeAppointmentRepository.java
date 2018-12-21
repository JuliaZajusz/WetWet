package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.EmployeeAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAppointmentRepository extends JpaRepository<EmployeeAppointment, Long> {
}
