package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT r FROM Employee r WHERE user_name =?1")
    Optional<Employee> findByUserName(String username);
}
