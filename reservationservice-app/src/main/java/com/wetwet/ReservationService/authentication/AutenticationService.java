package com.wetwet.ReservationService.authentication;

import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.repository.CredentialsRepository;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import com.wetwet.ReservationService.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutenticationService {
    private final EmployeeRepository employeeRepository;
    private final CredentialsRepository credentialsRepository;
    private final PositionRepository positionRepository;


    public AutenticationService(EmployeeRepository employeeRepository, CredentialsRepository credentialsRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.credentialsRepository = credentialsRepository;
        this.positionRepository = positionRepository;
    }


    public Optional<Employee> getUserByUsername(String username) {
        return this.employeeRepository.findByUserName(username);
    }
}
