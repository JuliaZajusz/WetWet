package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {
}
