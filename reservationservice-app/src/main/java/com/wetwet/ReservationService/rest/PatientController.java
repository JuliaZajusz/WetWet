package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.dto.PatientDTO;
import com.wetwet.ReservationService.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<PatientDTO> getAllPatients(){
        return patientService.getPatients();
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody
    List<PatientDTO> getPatronAllPatients(@PathVariable Long id) {
        return patientService.getPatronAllPatients(id);
    }

    @GetMapping(path ="/{id}")
    public @ResponseBody
    PatientDTO getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

}
