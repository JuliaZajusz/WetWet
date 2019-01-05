package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public Optional<Employee> findById(Long employeeId) {
        return repository.findById(employeeId);
    }
}
