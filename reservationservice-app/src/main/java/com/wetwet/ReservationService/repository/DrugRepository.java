package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {

}
