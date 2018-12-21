package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.PatientAppointment;
import com.wetwet.ReservationService.database.PatronAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatronAppointmentRepository extends JpaRepository<PatronAppointment, Long> {

    @Query("SELECT r FROM PatronAppointment r WHERE patron_id =?1")
    List<PatronAppointment> findAllByPatronId(Long patronId);
}
