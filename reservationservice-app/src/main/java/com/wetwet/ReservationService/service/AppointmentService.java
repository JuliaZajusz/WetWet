package com.wetwet.ReservationService.service;


import com.wetwet.ReservationService.database.*;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;
    @Autowired
    private PatronAppointmentRepository patronAppointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAppointmentRepository employeeAppointmentRepository;

    public List<Appointment> getAppointmentsByDate(String date) {

        List<Appointment> appointments = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDate().toString().equals(date)
                )
                .collect(Collectors.toList());
        return appointments;
    }


    public List<AppointmentWithPatientAndAddress> getAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentWithPatientAndAddress> appointmentWithPatientAndAddresses = appointments.stream()
                .map(appW -> {
                            Optional<PatientAppointment> opPatientAppointment = patientAppointmentRepository.findById(appW.getId());
                            Optional<PatronAppointment> opPatronAppointment = patronAppointmentRepository.findById(appW.getId());
                            Optional<EmployeeAppointment> opEmployeeAppointment = employeeAppointmentRepository.findById(appW.getId());

                            PatientAppointment patientAppointment = opPatientAppointment.isPresent() ? opPatientAppointment.get() : null;
                            PatronAppointment patronAppointment = opPatronAppointment.isPresent() ? opPatronAppointment.get() : null;
                            EmployeeAppointment employeeAppointment = opEmployeeAppointment.isPresent() ? opEmployeeAppointment.get() : null;

                            Long patientId = patientAppointment != null ? patientAppointment.getPatientId() : null;
                            Long patronId = patronAppointment != null ? patronAppointment.getPatronId() : null;
                            Long employeeId = employeeAppointment != null ? employeeAppointment.getEmployeeId() : null;
                            return new AppointmentWithPatientAndAddress(appW, patientId, patronId, employeeId);
                        }
                )
                .collect(Collectors.toList());
        return appointmentWithPatientAndAddresses;
    }


    public Appointment createAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress) {
        if (appointmentWithPatientAndAddress.id != null) {
            return updateAppointment(appointmentWithPatientAndAddress);
        }
        Appointment appointment = new Appointment(appointmentWithPatientAndAddress);
        Appointment appointment1 = appointmentRepository.save(appointment);

        Patient patient = patientRepository.findById(appointmentWithPatientAndAddress.patientId).get();
        if (patient != null) {
            PatientAppointment patientAppointment;
            patientAppointment = new PatientAppointment(patient.getId(), appointment1.getId());
            patientAppointmentRepository.save(patientAppointment);
        }

        Patron patron = patronRepository.findById(appointmentWithPatientAndAddress.patronId).get();
        if (patron != null) {
            PatronAppointment patronAppointment;
            patronAppointment = new PatronAppointment(patron.getId(), appointment1.getId());
            patronAppointmentRepository.save(patronAppointment);
        }

        Employee employee = employeeRepository.findById(appointmentWithPatientAndAddress.employeeId).get();
        if (employee != null) {
            EmployeeAppointment employeeAppointment;
            employeeAppointment = new EmployeeAppointment(employee.getId(), appointment1.getId());
            employeeAppointmentRepository.save(employeeAppointment);
        }

        return appointment1;
    }

    public Appointment updateAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress) {
        Appointment appointment = new Appointment(appointmentWithPatientAndAddress);


        Long appointmentId = appointmentWithPatientAndAddress.id;

        PatientAppointment oldPatientAppointment = patientAppointmentRepository.findById(appointmentId).orElse(null);
        if (oldPatientAppointment != null &&
                appointmentWithPatientAndAddress.patientId != oldPatientAppointment.getPatientId()) {
            patientAppointmentRepository.delete(oldPatientAppointment);
        }
        Patient patient = patientRepository.findById(appointmentWithPatientAndAddress.patientId).orElseThrow(() -> new IllegalArgumentException("patientId"));
        PatientAppointment patientAppointment;
        patientAppointment = new PatientAppointment(patient.getId(), appointment.getId());
        patientAppointmentRepository.save(patientAppointment);


        PatronAppointment oldPatronAppointment = patronAppointmentRepository.findById(appointmentId).orElse(null);
        if (oldPatronAppointment != null &&
                appointmentWithPatientAndAddress.patronId != oldPatronAppointment.getPatronId()) {
            patronAppointmentRepository.delete(oldPatronAppointment);
        }
        Patron patron = patronRepository.findById(appointmentWithPatientAndAddress.patronId).orElseThrow(() -> new IllegalArgumentException("patronId"));
        PatronAppointment patronAppointment;
        patronAppointment = new PatronAppointment(patron.getId(), appointment.getId());
        patronAppointmentRepository.save(patronAppointment);


        EmployeeAppointment oldEmployeeAppointment = employeeAppointmentRepository.findById(appointmentId).orElse(null);
        if (oldEmployeeAppointment != null &&
                appointmentWithPatientAndAddress.employeeId != oldEmployeeAppointment.getEmployeeId()) {
            employeeAppointmentRepository.delete(oldEmployeeAppointment);
        }
        Employee employee = employeeRepository.findById(appointmentWithPatientAndAddress.employeeId).orElseThrow(() -> new IllegalArgumentException("employeeId"));
        EmployeeAppointment employeeAppointment;
        employeeAppointment = new EmployeeAppointment(employee.getId(), appointment.getId());
        employeeAppointmentRepository.save(employeeAppointment);

        Appointment appointment1 = appointmentRepository.save(appointment);
        return appointment1;
    }


    public void deleteAppointment(Long id) {
        PatientAppointment oldPatientAppointment = patientAppointmentRepository.findById(id).orElse(null);
        if (oldPatientAppointment != null) {
            patientAppointmentRepository.delete(oldPatientAppointment);
        }
        PatronAppointment oldPatronAppointment = patronAppointmentRepository.findById(id).orElse(null);
        if (oldPatronAppointment != null) {
            patronAppointmentRepository.delete(oldPatronAppointment);
        }
        EmployeeAppointment oldEmployeeAppointment = employeeAppointmentRepository.findById(id).orElse(null);
        if (oldEmployeeAppointment != null) {
            employeeAppointmentRepository.delete(oldEmployeeAppointment);
        }
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentWithPatientAndAddress> getPatientAppointments(Long patientId) {
        List<PatientAppointment> patientAppointments = patientAppointmentRepository.findAllByPatientId(patientId);
        List<AppointmentWithPatientAndAddress> appointments = patientAppointments
                .stream()
                .map(patientAppointment ->
                        getAppointment(patientAppointment.getAppointmentId())
                )
                .collect(Collectors.toList());
        return appointments;
    }

    public AppointmentWithPatientAndAddress getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("appointmentId"));

        Optional<PatientAppointment> opPatientAppointment = patientAppointmentRepository.findById(appointment.getId());
        Optional<PatronAppointment> opPatronAppointment = patronAppointmentRepository.findById(appointment.getId());
        Optional<EmployeeAppointment> opEmployeeAppointment = employeeAppointmentRepository.findById(appointment.getId());

        PatientAppointment patientAppointment = opPatientAppointment.isPresent() ? opPatientAppointment.get() : null;
        PatronAppointment patronAppointment = opPatronAppointment.isPresent() ? opPatronAppointment.get() : null;
        EmployeeAppointment employeeAppointment = opEmployeeAppointment.isPresent() ? opEmployeeAppointment.get() : null;

        Long patientId = patientAppointment != null ? patientAppointment.getPatientId() : null;
        Long patronId = patronAppointment != null ? patronAppointment.getPatronId() : null;
        Long employeeId = employeeAppointment != null ? employeeAppointment.getEmployeeId() : null;
        return new AppointmentWithPatientAndAddress(appointment, patientId, patronId, employeeId);

    }
}
