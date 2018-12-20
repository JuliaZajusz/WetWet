package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {

}
