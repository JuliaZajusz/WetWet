package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.dto.PatientDTO;
import com.wetwet.ReservationService.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repository;
    private final BreedService breedService;

    public PatientService(PatientRepository repository, BreedService breedService) {
        this.repository = repository;
        this.breedService = breedService;
    }

    public List<PatientDTO> getPatients(){
        List<PatientDTO> patients = new ArrayList<>();
        for(Patient patient : repository.findAll()){
            PatientDTO tmp = new PatientDTO();
            BeanUtils.copyProperties(patient, tmp);
            tmp.setBreed(breedService.getBreedDTOById(patient.getBreedId()));
            patients.add(tmp);
        }
        return patients;
    }
    public PatientDTO getPatientById(long id){
        return getPatients().stream().filter(patient -> patient.getId() == id).findFirst().get();
    }
}
