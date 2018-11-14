package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Species;

import com.wetwet.ReservationService.dto.SpeciesDTO;
import com.wetwet.ReservationService.repository.SpeciesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {
    private final SpeciesRepository repository;

    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    public List<Species> getSpecies(){
        return repository.findAll();
    }

    public Species getSpeciesById(long id){
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    public SpeciesDTO getSpeciesDTOById(long id){
        SpeciesDTO speciesDTO = new SpeciesDTO();
        Species species = repository.findById(id).get();
        BeanUtils.copyProperties(species, speciesDTO);
        return speciesDTO;
    }
}
