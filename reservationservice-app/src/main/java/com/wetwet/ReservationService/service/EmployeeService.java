package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository repository;
    @Autowired
    private EntityManagerUtils emUtils;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public List<Employee> getEmployees() {
        repository = emUtils.getJpaFactory().getRepository(EmployeeRepository.class);
        return repository.findAll();
    }

//    public Optional<Employee> getEmployeeById(long id) {
//        return repository.findById(id);
//    }
//
//    public Employee createEmployee(Employee employee) {
//        return repository.save(employee);
//    }
}
