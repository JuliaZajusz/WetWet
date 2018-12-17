package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
