package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.repository.ConsultingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConsultingRoomService {
    @Autowired
    private ConsultingRoomRepository repository;

    public ConsultingRoom createConsultingRoom(ConsultingRoom consultingRoom) {
        return repository.save(consultingRoom);
    }

    public List<ConsultingRoom> getConsultingRooms() {
        return repository.findAll();
    }

    public ConsultingRoom getConsultingRoomById(Long id) {
        return repository.findById(id).orElseGet(null);
    }
}
