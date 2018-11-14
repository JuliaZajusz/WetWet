package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {

}
