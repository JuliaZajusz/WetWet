package com.wetwet.ReservationService.rest.Breed;

import com.wetwet.ReservationService.database.Breed;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepository extends CrudRepository<Breed, Long> {
}
