package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
//    @Autowired
//    private EmployeeRepository repository;
//    @Autowired
//    private HttpServletRequest context;
//    @Autowired
//    private EntityManagerUtils emUtils;


    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('NONE')")
    public List<Employee> getAllEmployees(HttpServletRequest request) {
//        System.out.println(request.isUserInRole("NONE"));
//        System.out.println(request.isUserInRole("ROLE_NONE"));
//        System.out.println(context.getUserPrincipal());
//        System.out.println(context.getRemoteUser());
//        System.out.println(context.isUserInRole("NONE"));
//        System.out.println(context.isUserInRole("ROLE_NONE"));

//        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
//                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return employeeService.getEmployees();
//        setRepository();
//        return repository.findAll();
    }

//    private void setRepository() {
//        repository = emUtils.getJpaFactory().getRepository(EmployeeRepository.class);
//    }
}
