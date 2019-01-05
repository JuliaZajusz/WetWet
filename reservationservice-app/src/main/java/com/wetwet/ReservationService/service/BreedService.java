package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Breed;
import com.wetwet.ReservationService.dto.BreedDTO;
import com.wetwet.ReservationService.repository.BreedRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BreedService {
    @Autowired
    private BreedRepository repository;
    @Autowired
    private SpeciesService speciesService;

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
        breedDTO.setSpecies(speciesService.getSpeciesById(breed.getSpeciesId()).getName());
        return breedDTO;
    }
}
