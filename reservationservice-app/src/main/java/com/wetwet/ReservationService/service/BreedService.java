package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Breed;
import com.wetwet.ReservationService.dto.BreedDTO;
import com.wetwet.ReservationService.repository.BreedRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedService {
    private final BreedRepository repository;
    private final SpeciesService speciesService;

    public BreedService(BreedRepository repository, SpeciesService speciesService) {
        this.repository = repository;
        this.speciesService = speciesService;
    }

    public Breed getBreedById(long id){
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    public List<Breed> getBreeds(){
        return repository.findAll();
    }

    public BreedDTO getBreedDTOById(long id){
        BreedDTO breedDTO = new BreedDTO();
        Breed breed = repository.findById(id).get();
        BeanUtils.copyProperties(breed, breedDTO);
        breedDTO.setSpecies(speciesService.getSpeciesDTOById(breed.getSpeciesId()));
        return breedDTO;
    }
}
