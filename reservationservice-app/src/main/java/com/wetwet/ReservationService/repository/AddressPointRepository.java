package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.AddressPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressPointRepository extends JpaRepository<AddressPoint, Long> {
}
