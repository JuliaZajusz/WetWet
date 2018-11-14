package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}
