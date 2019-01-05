package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.database.EmployeeAppointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
import com.wetwet.ReservationService.repository.EmployeeAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EmployeeAppointmentService {
    @Autowired
    private EmployeeAppointmentRepository repository;

    @Autowired
    private EmployeeService employeeService;

    public Optional<EmployeeAppointment> findById(Long id) {
        return repository.findById(id);
    }

    public EmployeeAppointment save(EmployeeAppointment employeeAppointment) {
        return repository.save(employeeAppointment);
    }

    public void updateEmployeeAppointment(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment, Long appointmentId) {
        EmployeeAppointment oldEmployeeAppointment = repository.findById(appointmentId).orElse(null);
        if (oldEmployeeAppointment != null &&
                appointmentWithPatientAndAddress.employeeId != oldEmployeeAppointment.getEmployeeId()) {
            repository.delete(oldEmployeeAppointment);
        }
        Employee employee = employeeService.findById(appointmentWithPatientAndAddress.employeeId).orElseThrow(() -> new IllegalArgumentException("employeeId"));
        EmployeeAppointment employeeAppointment;
        employeeAppointment = new EmployeeAppointment(employee.getId(), appointment.getId());
        repository.save(employeeAppointment);
    }

    public void deleteEmployeeAppointment(Long id) {
        EmployeeAppointment oldEmployeeAppointment = repository.findById(id).orElse(null);
        if (oldEmployeeAppointment != null) {
            repository.delete(oldEmployeeAppointment);
        }
    }

    public Long getAppointmentEmployeeId(Appointment appW) {
        Optional<EmployeeAppointment> opEmployeeAppointment = repository.findById(appW.getId());
        EmployeeAppointment employeeAppointment = opEmployeeAppointment.isPresent() ? opEmployeeAppointment.get() : null;
        return employeeAppointment != null ? employeeAppointment.getEmployeeId() : null;
    }

    public void assignAppointmentToEmployee(AppointmentWithPatientAndAddress appointmentWithPatientAndAddress, Appointment appointment1) {
        Employee employee = employeeService.findById(appointmentWithPatientAndAddress.employeeId).get();
        if (employee != null) {
            EmployeeAppointment employeeAppointment;
            employeeAppointment = new EmployeeAppointment(employee.getId(), appointment1.getId());
            repository.save(employeeAppointment);
        }
    }
}
