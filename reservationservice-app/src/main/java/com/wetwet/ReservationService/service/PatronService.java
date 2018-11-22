package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.repository.PatronRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatronService {
    private final PatronRepository repository;

    public PatronService(PatronRepository repository) {
        this.repository = repository;
    }


    public List<Patron> getPatrons() {
        return repository.findAll();
    }

    public Optional<Patron> getPatronById(long id) {
        return repository.findById(id);
    }

    public Patron createPatron(Patron patron) {
        return repository.save(patron);
    }
}
