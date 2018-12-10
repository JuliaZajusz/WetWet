package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.dto.PatientDTO;
import com.wetwet.ReservationService.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository repository;
    private final BreedService breedService;
    @Autowired
    private EntityManagerUtils emUtils;

    public PatientService(PatientRepository repository, BreedService breedService) {
        this.repository = repository;
        this.breedService = breedService;
    }

    public List<PatientDTO> getPatients(){
        repository = emUtils.getJpaFactory().getRepository(PatientRepository.class);
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
        repository = emUtils.getJpaFactory().getRepository(PatientRepository.class);
        return getPatients().stream().filter(patient -> patient.getId() == id).findFirst().get();
    }
}
