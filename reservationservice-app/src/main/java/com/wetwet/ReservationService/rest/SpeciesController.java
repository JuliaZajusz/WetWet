package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Species;
import com.wetwet.ReservationService.service.SpeciesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/species")
public class SpeciesController {
    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<Species> getAllSpecies(){
        return speciesService.getSpecies();
    }
}
