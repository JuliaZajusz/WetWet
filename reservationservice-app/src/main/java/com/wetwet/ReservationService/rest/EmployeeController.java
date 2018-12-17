package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('NONE')")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }
}
