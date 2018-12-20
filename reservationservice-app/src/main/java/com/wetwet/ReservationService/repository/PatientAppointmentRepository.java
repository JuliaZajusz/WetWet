package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.PatientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Long> {

    @Query("SELECT r FROM PatientAppointment r WHERE patient_id =?1")
    List<PatientAppointment> findAllByPatientId(Long patientId);
}
