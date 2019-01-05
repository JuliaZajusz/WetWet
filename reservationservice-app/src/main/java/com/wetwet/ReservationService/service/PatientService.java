package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Patient;
import com.wetwet.ReservationService.database.PatientPatron;
import com.wetwet.ReservationService.dto.PatientDTO;
import com.wetwet.ReservationService.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository repository;
    @Autowired
    private PatientPatronService patientPatronService;
    @Autowired
    private BreedService breedService;

    public List<PatientDTO> getPatients(){
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : repository.findAll()) {
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

    public List<Patient> getPatientsByPatronId(long id) {
        List<PatientPatron> patientPatrons = patientPatronService.getPatientPatronByPatronId(id);
        List<Long> patientsIds = patientPatrons
                .stream()
                .map(patientPatron -> patientPatron.getPatientId()).collect(Collectors.toList());
        return repository.findAll().stream()
                .filter(patient -> patientsIds.contains(patient.getId()))
                .collect(Collectors.toList());
    }

    public List<PatientDTO> getPatronAllPatients(Long id) {
        return getPatientsByPatronId(id).stream()
                .map(patient -> new PatientDTO(patient, breedService.getBreedDTOById(patient.getBreedId()))).collect(Collectors.toList());
    }

    public Optional<Patient> findById(Long patientId) {
        return repository.findById(patientId);
    }
}
