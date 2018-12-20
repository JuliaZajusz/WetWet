package com.wetwet.ReservationService.service;


import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.database.PatientAppointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.AppointmentRepository;
import com.wetwet.ReservationService.repository.PatientAppointmentRepository;
import com.wetwet.ReservationService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientAppointmentRepository patientAppointmentRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientAppointmentRepository = patientAppointmentRepository;
        this.patientRepository = patientRepository;
    }


    public List<Appointment> getAppointments() {

        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress) {
        Appointment appointment = new Appointment(appointmentWithPatientAndAddress);
        appointment = appointmentRepository.save(appointment);
        Patient patient = patientRepository.findById(appointmentWithPatientAndAddress.patientId).get();
        if (patient != null) {
            PatientAppointment patientAppointment;
            patientAppointment = new PatientAppointment(patient.getId(), appointment.getId());
            patientAppointmentRepository.save(patientAppointment);
        }
        return appointment;
    }


    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        return patientAppointmentRepository.findAllByPatientId(patientId)
                .stream()
                .map(patientAppointment -> appointmentRepository.findById(patientAppointment.getAppointmentId()).get()).collect(Collectors.toList());
    }
}
