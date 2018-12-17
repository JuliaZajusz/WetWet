package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
