package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
