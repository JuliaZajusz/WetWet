package com.wetwet.ReservationService.authentication;

import com.wetwet.ReservationService.database.Credentials;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.repository.CredentialsRepository;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import com.wetwet.ReservationService.repository.PositionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

//    public AutenticationService(EntityManagerUtils emUtil) {
//        this.employeeRepository = emUtil.getFourthDatabaseJpaFactory().getRepository(EmployeeRepository.class);
//        this.credentialsRepository = emUtil.getFourthDatabaseJpaFactory().getRepository(CredentialsRepository.class);
//        this.positionRepository = emUtil.getFourthDatabaseJpaFactory().getRepository(PositionRepository.class);
//    }

    public Optional<Credentials> getByLogin(String login) {
        return this.credentialsRepository.findByLogin(login);
    }

    public boolean existsByLogin(String login) {
        return this.credentialsRepository.findByLogin(login).isPresent();
    }

    public Credentials createUser(CredentialsDTO userVM, Employee employee) {
        Employee e = employeeRepository.save(employee);
        Credentials c = credentialsRepository.save(new Credentials(userVM.getLogin(), userVM.getPassword(), e.getId()));
        return c;
    }
}
