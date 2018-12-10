package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Species;
import com.wetwet.ReservationService.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpeciesService {
    private SpeciesRepository repository;
    @Autowired
    private EntityManagerUtils emUtils;


    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    public List<Species> getSpecies(){
        repository = emUtils.getJpaFactory().getRepository(SpeciesRepository.class);
        return repository.findAll();
    }

    public Species getSpeciesById(long id){
        repository = emUtils.getJpaFactory().getRepository(SpeciesRepository.class);
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }
}
