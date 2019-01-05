package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.database.PatientAppointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.PatientAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientAppointmentService {
    @Autowired
    private PatientAppointmentRepository repository;
    @Autowired
    private PatientService patientService;

    public Optional<PatientAppointment> findById(Long id) {
        return repository.findById(id);
    }

    public PatientAppointment save(PatientAppointment patientAppointment) {
        return repository.save(patientAppointment);
    }

    public void updatePatientAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment, Long appointmentId) {
        PatientAppointment oldPatientAppointment = repository.findById(appointmentId).orElse(null);
        if (oldPatientAppointment != null &&
                appointmentWithPatientAndAddress.patientId != oldPatientAppointment.getPatientId()) {
            repository.delete(oldPatientAppointment);
        }
        Patient patient = patientService.findById(appointmentWithPatientAndAddress.patientId).orElseThrow(() -> new IllegalArgumentException("patientId"));
        PatientAppointment patientAppointment;
        patientAppointment = new PatientAppointment(patient.getId(), appointment.getId());
        repository.save(patientAppointment);
    }

    public void deletePatientAppointment(Long id) {
        PatientAppointment oldPatientAppointment = repository.findById(id).orElse(null);
        if (oldPatientAppointment != null) {
            repository.delete(oldPatientAppointment);
        }
    }

    public List<PatientAppointment> findAllByPatientId(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }

    public Long getAppointmentPatientId(Appointment appW) {
        Optional<PatientAppointment> opPatientAppointment = repository.findById(appW.getId());
        PatientAppointment patientAppointment = opPatientAppointment.isPresent() ? opPatientAppointment.get() : null;
        return patientAppointment != null ? patientAppointment.getPatientId() : null;
    }


    public void assignAppointmentToPatient(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment1) {
        Patient patient = patientService.findById(appointmentWithPatientAndAddress.patientId).get();
        if (patient != null) {
            PatientAppointment patientAppointment;
            patientAppointment = new PatientAppointment(patient.getId(), appointment1.getId());
            repository.save(patientAppointment);
        }
    }
}

