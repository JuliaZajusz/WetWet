package com.wetwet.ReservationService.service;


import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.PatientAppointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private PatientAppointmentService patientAppointmentService;
    @Autowired
    private PatronAppointmentService patronAppointmentService;
    @Autowired
    private EmployeeAppointmentService employeeAppointmentService;

    public List<Appointment> getAppointmentsByDate(String date) {
        List<Appointment> appointments = repository.findAll().stream()
                .filter(appointment -> appointment.getDate().toString().equals(date))
                .collect(Collectors.toList());
        return appointments;
    }


    public List<AppointmentWithPatientAndAddress> getAppointments() {
        List<Appointment> appointments = repository.findAll();
        List<AppointmentWithPatientAndAddress> appointmentWithPatientAndAddresses = appointments.stream()
                .map(appW -> {
                    Long patientId = patientAppointmentService.getAppointmentPatientId(appW);
                    Long patronId = patronAppointmentService.getAppointmentPatronId(appW);
                    Long employeeId = employeeAppointmentService.getAppointmentEmployeeId(appW);
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
        Appointment appointment1 = repository.save(appointment);
        patientAppointmentService.assignAppointmentToPatient(appointmentWithPatientAndAddress, appointment1);
        patronAppointmentService.assignAppointmentToPatron(appointmentWithPatientAndAddress, appointment1);
        employeeAppointmentService.assignAppointmentToEmployee(appointmentWithPatientAndAddress, appointment1);
        return appointment1;
    }

    public Appointment updateAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress) {
        Appointment appointment = new Appointment(appointmentWithPatientAndAddress);
        Long appointmentId = appointmentWithPatientAndAddress.id;
        patientAppointmentService.updatePatientAppointment(appointmentWithPatientAndAddress, appointment, appointmentId);
        patronAppointmentService.updatePatronAppointment(appointmentWithPatientAndAddress, appointment, appointmentId);
        employeeAppointmentService.updateEmployeeAppointment(appointmentWithPatientAndAddress, appointment, appointmentId);
        Appointment appointment1 = repository.save(appointment);
        return appointment1;
    }

    public void deleteAppointment(Long id) {
        patientAppointmentService.deletePatientAppointment(id);
        patronAppointmentService.deletePatronAppointment(id);
        employeeAppointmentService.deleteEmployeeAppointment(id);
        repository.deleteById(id);
    }

    public List<AppointmentWithPatientAndAddress> getPatientAppointments(Long patientId) {
        List<PatientAppointment> patientAppointments = patientAppointmentService.findAllByPatientId(patientId);
        List<AppointmentWithPatientAndAddress> appointments = patientAppointments
                .stream()
                .map(patientAppointment ->
                        getAppointment(patientAppointment.getAppointmentId())
                )
                .collect(Collectors.toList());
        return appointments;
    }

    public AppointmentWithPatientAndAddress getAppointment(Long id) {
        Appointment appointment = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("appointmentId"));
        Long patientId = patientAppointmentService.getAppointmentPatientId(appointment);
        Long patronId = patronAppointmentService.getAppointmentPatronId(appointment);
        Long employeeId = employeeAppointmentService.getAppointmentEmployeeId(appointment);
        return new AppointmentWithPatientAndAddress(appointment, patientId, patronId, employeeId);
    }
}
