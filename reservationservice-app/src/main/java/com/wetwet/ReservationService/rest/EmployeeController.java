package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import com.wetwet.ReservationService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private HttpServletRequest context;
    @Autowired
    private EntityManagerUtils emUtils;


    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {

//        return employeeService.getEmployees();
        setRepository(context.getRequestURL().toString());
        return repository.findAll();
    }

    private void setRepository(String url) {
        repository = emUtils.getJpaFactory(url).getRepository(EmployeeRepository.class);
    }
}
