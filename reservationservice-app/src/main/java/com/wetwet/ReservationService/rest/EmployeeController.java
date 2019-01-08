package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.database.WetDate;
import com.wetwet.ReservationService.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path ="/{id}")
    public @ResponseBody
    Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/all")
    public List<Employee> getAllAvailableEmployees(@Valid @RequestBody WetDate date) {
        return employeeService.getAllAvailableEmployees(date);
    }
}
