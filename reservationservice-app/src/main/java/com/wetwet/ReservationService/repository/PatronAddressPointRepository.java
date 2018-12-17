package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.PatronAddressPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatronAddressPointRepository extends JpaRepository<PatronAddressPoint, Long> {

    @Query("SELECT r FROM PatronAddressPoint r WHERE Patron_ID =?1")
    List<PatronAddressPoint> getByPatronId(long id);
}
