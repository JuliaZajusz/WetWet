package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultingRoomInaccessibilityRepository extends JpaRepository<ConsultingRoomInaccessibility, Long> {

    @Query("SELECT r FROM ConsultingRoomInaccessibility r WHERE Consulting_Room_ID =?1")
    List<ConsultingRoomInaccessibility> findAllByConsultingRoomId(long id);
}
