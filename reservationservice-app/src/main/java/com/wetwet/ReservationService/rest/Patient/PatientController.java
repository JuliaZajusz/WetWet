package com.wetwet.ReservationService.rest.Patient;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/patients")
public class PatientController {
    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Patient> getAllPatients(){
        return patientsRepository.findAll();
    }
}
