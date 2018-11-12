package com.wetwet.ReservationService.rest.Patient;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PatientsRepository extends CrudRepository<Patient, Long> {

}
