package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {

    @Query("SELECT r FROM EmployeeAvailability r WHERE Employee_ID =?1")
    List<EmployeeAvailability> findAllByEmployeeId(long id);
}
