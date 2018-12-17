package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.dto.PatronWithPetsDTO;
import com.wetwet.ReservationService.repository.PatronRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatronService {
    private PatronRepository repository;
    private PatientService patientService;
    private AddressPointService addressPointService;

    public PatronService(PatronRepository repository, PatientService patientService, AddressPointService addressPointService) {
        this.repository = repository;
        this.patientService = patientService;
        this.addressPointService = addressPointService;
    }


    public List<Patron> getPatrons() {
        return repository.findAll();
    }

    public PatronWithPetsDTO getPatronById(long id) {
        Optional<Patron> patron = repository.findById(id);
        if (!patron.isPresent()) {
            return null;
        }
        PatronWithPetsDTO p = new PatronWithPetsDTO(patron.get());
        p.setPets(patientService.getPatientsByPatronId(id));
        p.setAddresses(addressPointService.getAddressById(id));
        return p;
    }

    public Patron createPatron(Patron patron) {
        return repository.save(patron);
    }

    public List<PatronWithPetsDTO> getPatronsWithDetails() {
        List<PatronWithPetsDTO> patrons = repository.findAll()
                .stream()
                .map(patron -> {
                    PatronWithPetsDTO p = new PatronWithPetsDTO(patron);
                    p.setPets(patientService.getPatientsByPatronId(patron.getId()));
                    return p;
                })
                .collect(Collectors.toList());
        return patrons;
    }
}
