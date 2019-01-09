package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.database.EmployeeAvailability;
import com.wetwet.ReservationService.database.WetDate;
import com.wetwet.ReservationService.dto.EmployeeWithAvailability;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class  EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeAvailabilityService employeeAvailabilityService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EmployeeAppointmentService employeeAppointmentService;

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public Optional<Employee> findById(Long employeeId) {
        return repository.findById(employeeId);
    }

    public List<EmployeeWithAvailability> getAvailabilities() {
        List<EmployeeAvailability> employeeAvailabilities = employeeAvailabilityService.getEmployeeAvailabilities();
        List<EmployeeWithAvailability> employeeWithAvailabilities = employeeAvailabilities.stream()
                .map(employeeAvailability ->
                        {
                            Employee c = repository.findById(employeeAvailability.getEmployeeId()).orElseGet(null);
                            return new EmployeeWithAvailability(employeeAvailability, c);
                        }
                ).collect(Collectors.toList());
        return employeeWithAvailabilities;
    }

    public EmployeeWithAvailability getAvailabilityById(Long id) {
        EmployeeAvailability employeeAvailability = employeeAvailabilityService.getEmployeeAvailability(id);
        Employee c = repository.findById(employeeAvailability.getEmployeeId()).orElseGet(null);
        return new EmployeeWithAvailability(employeeAvailability, c);
    }


    public List<Employee> getAllAvailableEmployees(WetDate date) {
        List<Employee> allEmployees = repository.findAll();
        List<Employee> availableEmployees = allEmployees.stream()
                .filter(employee -> {
                    List<EmployeeAvailability> employeeAvailabilities = employeeAvailabilityService.findAllByEmployeeId(employee.getId());
                    return checkIfWetDateContainsAtLEastOneFromList(date, employeeAvailabilities);
                }).collect(Collectors.toList());


        List<Appointment> appointmentsByDay = appointmentService.getAppointmentsByDate(date.date.toString());

        List<Appointment> appointmentsInTheSameTime = filterByAppointmentDateContainsWetDate(date, appointmentsByDay);

        List<Employee> freeEmployees = availableEmployees.stream()
                .filter(employee -> {
                            return !appointmentsInTheSameTime.stream()
                                    .anyMatch(appointment -> {
                                        boolean isTheSameEmployee = employeeAppointmentService.getAppointmentEmployeeId(appointment) != null && employeeAppointmentService.getAppointmentEmployeeId(appointment) == employee.getId(); //EmployeeAppointment?
                                        return isTheSameEmployee;
                                    });
                        }
                ).collect(Collectors.toList());
        return freeEmployees;
    }

    public List<Appointment> filterByAppointmentDateContainsWetDate(WetDate a, List<Appointment> appointments) {
        return appointments.stream()
                .filter(appointment -> {
                            WetDate wetDate = new WetDate(appointment);
                            return checkIfWetDateContainsAnother(wetDate, a);
                        }
                ).collect(Collectors.toList());
    }

    public boolean checkIfWetDateContainsAtLEastOneFromList(WetDate a, List<EmployeeAvailability> availabilities) {
        return availabilities.stream().map(availability -> new WetDate(availability))
                .anyMatch(wetDate -> checkIfWetDateContainsAnother(wetDate, a));
    }

    public boolean checkIfWetDateContainsAnother(WetDate availability, WetDate appointment) {
        System.out.println(availability.date + "  " + availability.startTime + "  " + availability.endTime);
        System.out.println(appointment.date + "  " + appointment.startTime + "  " + appointment.endTime);
        if (availability.date.toString().equals(appointment.date.toString())) {

            if (availability.startTime.before(appointment.startTime) && availability.endTime.after(appointment.startTime)) {
                System.out.println("Poczatek wizyty wpada w niedostepnosc");
                return true;
            }
            if (availability.startTime.after(appointment.startTime) && availability.startTime.before(appointment.endTime)) {
                System.out.println("Koniec wizyty wpada w niedostepnosc");
                return true;
            }

            if (availability.startTime.before(appointment.startTime) && availability.endTime.after(appointment.endTime)) {
                System.out.println("Srodek wizyty wpada w niedostepnosc");
                return true;
            }
            return false;
        }
        return false;
    }
}
