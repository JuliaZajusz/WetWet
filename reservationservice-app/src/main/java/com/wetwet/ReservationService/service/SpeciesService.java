package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Species;
import com.wetwet.ReservationService.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpeciesService {
    private SpeciesRepository repository;

    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    public List<Species> getSpecies(){
        return repository.findAll();
    }

    public Species getSpeciesById(long id){
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }
}
