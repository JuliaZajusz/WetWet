package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatronService {
    private PatronRepository repository;
    @Autowired
    private EntityManagerUtils emUtils;

    public PatronService(PatronRepository repository) {
        this.repository = repository;
    }


    public List<Patron> getPatrons() {
        repository = emUtils.getJpaFactory().getRepository(PatronRepository.class);
        return repository.findAll();
    }

    public Optional<Patron> getPatronById(long id) {
        repository = emUtils.getJpaFactory().getRepository(PatronRepository.class);
        return repository.findById(id);
    }

    public Patron createPatron(Patron patron) {
        repository = emUtils.getJpaFactory().getRepository(PatronRepository.class);
        return repository.save(patron);
    }
}
