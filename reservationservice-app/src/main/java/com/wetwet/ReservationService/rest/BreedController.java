package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Breed;
import com.wetwet.ReservationService.service.BreedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/breed")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<Breed> getAllBreeds(){
        return breedService.getBreeds();
    }

    public @ResponseBody Breed getBreedById(long id){
        return breedService.getBreedById(id);
    }
}
