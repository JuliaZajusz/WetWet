package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.ConsultingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultingRoomRepository extends JpaRepository<ConsultingRoom, Long> {
}
