package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.database.PatronAppointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.PatronAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PatronAppointmentService {
    @Autowired
    private PatronAppointmentRepository repository;
    @Autowired
    private PatronService patronService;

    public Optional<PatronAppointment> findById(Long id) {
        return repository.findById(id);
    }

    public PatronAppointment save(PatronAppointment patronAppointment) {
        return repository.save(patronAppointment);
    }

    public void updatePatronAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment, Long appointmentId) {
        PatronAppointment oldPatronAppointment = repository.findById(appointmentId).orElse(null);
        if (oldPatronAppointment != null &&
                appointmentWithPatientAndAddress.patronId != oldPatronAppointment.getPatronId()) {
            repository.delete(oldPatronAppointment);
        }
        Patron patron = patronService.findById(appointmentWithPatientAndAddress.patronId).orElseThrow(() -> new IllegalArgumentException("patronId"));
        PatronAppointment patronAppointment;
        patronAppointment = new PatronAppointment(patron.getId(), appointment.getId());
        repository.save(patronAppointment);
    }

    public void deletePatronAppointment(Long id) {
        PatronAppointment oldPatronAppointment = repository.findById(id).orElse(null);
        if (oldPatronAppointment != null) {
            repository.delete(oldPatronAppointment);
        }
    }

    public Long getAppointmentPatronId(Appointment appW) {
        Optional<PatronAppointment> opPatronAppointment = repository.findById(appW.getId());
        PatronAppointment patronAppointment = opPatronAppointment.isPresent() ? opPatronAppointment.get() : null;
        return patronAppointment != null ? patronAppointment.getPatronId() : null;
    }

    public void assignAppointmentToPatron(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment1) {
        Patron patron = patronService.findById(appointmentWithPatientAndAddress.patronId).get();
        if (patron != null) {
            PatronAppointment patronAppointment;
            patronAppointment = new PatronAppointment(patron.getId(), appointment1.getId());
            repository.save(patronAppointment);
        }
    }
}
