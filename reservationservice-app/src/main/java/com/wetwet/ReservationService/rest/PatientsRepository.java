package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PatientsRepository extends CrudRepository<Patient, Long> {
}
