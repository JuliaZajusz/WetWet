package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/patients")
public class PatientController {
    @Autowired
    private PatientsRepository patientsRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Patient> getAllPatients(){
        return patientsRepository.findAll();
    }
}
