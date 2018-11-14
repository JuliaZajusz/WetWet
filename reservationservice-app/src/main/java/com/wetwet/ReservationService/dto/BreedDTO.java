package com.wetwet.ReservationService.dto;

public class BreedDTO {

    private String name;

    private SpeciesDTO species;

    public BreedDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpeciesDTO getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDTO species) {
        this.species = species;
    }
}
