package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.PatientPatron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientPatronRepository extends JpaRepository<PatientPatron, Long> {

    @Query("SELECT r FROM PatientPatron r WHERE Patron_ID =?1")
    List<PatientPatron> getPatientPatronByPatronId(long id);
}
