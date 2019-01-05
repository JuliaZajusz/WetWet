package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.PatientPatron;
import com.wetwet.ReservationService.repository.PatientPatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientPatronService {
    @Autowired
    private PatientPatronRepository repository;


    public List<PatientPatron> getPatientPatronByPatronId(long id) {
        return repository.getPatientPatronByPatronId(id);
    }
}
