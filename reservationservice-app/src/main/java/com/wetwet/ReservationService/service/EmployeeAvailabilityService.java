package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.EmployeeAvailability;
import com.wetwet.ReservationService.repository.EmployeeAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeAvailabilityService {
    @Autowired
    private EmployeeAvailabilityRepository repository;

    public EmployeeAvailability createAvailability(EmployeeAvailability consultingRoomInaccessibility) {
        return repository.save(consultingRoomInaccessibility);
    }

    public List<EmployeeAvailability> findAllByEmployeeId(long id) {
        return repository.findAllByEmployeeId(id);
    }

    public List<EmployeeAvailability> getEmployeeAvailabilities() {
        return repository.findAll();
    }

    public EmployeeAvailability getEmployeeAvailability(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employeeAvailabilityId"));
    }
}
