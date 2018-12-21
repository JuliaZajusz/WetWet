package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.AppointmentDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDrugRepository extends JpaRepository<AppointmentDrug, Long> {
}
