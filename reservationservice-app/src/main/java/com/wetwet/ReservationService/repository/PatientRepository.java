package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
