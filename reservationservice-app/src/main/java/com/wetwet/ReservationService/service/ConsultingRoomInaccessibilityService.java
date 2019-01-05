package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.dto.ConsultingRoomInaccessibilityWthConsultingRoom;
import com.wetwet.ReservationService.repository.ConsultingRoomInaccessibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultingRoomInaccessibilityService {
    @Autowired
    private ConsultingRoomInaccessibilityRepository repository;
    @Autowired
    private ConsultingRoomService consultingRoomService;

    public List<ConsultingRoomInaccessibilityWthConsultingRoom> getInaccessibilities() {
        List<ConsultingRoomInaccessibility> consultingRoomInaccessibilities = repository.findAll();
        List<ConsultingRoomInaccessibilityWthConsultingRoom> consultingRoomInaccessibilityWthConsultingRooms = consultingRoomInaccessibilities.stream()
                .map(consultingRoomInaccessibility ->
                        {
                            ConsultingRoom c = consultingRoomService.findById(consultingRoomInaccessibility.getConsultingRoomId()).orElseGet(null);
                            return new ConsultingRoomInaccessibilityWthConsultingRoom(consultingRoomInaccessibility, c);
                        }
                ).collect(Collectors.toList());
        return consultingRoomInaccessibilityWthConsultingRooms;
    }

    public ConsultingRoomInaccessibility createInaccessibility(ConsultingRoomInaccessibility consultingRoomInaccessibility) {
        return repository.save(consultingRoomInaccessibility);
    }

    public ConsultingRoomInaccessibilityWthConsultingRoom getInaccessibilityById(Long id) {
        ConsultingRoomInaccessibility consultingRoomInaccessibility = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("consultingRoomInaccessibilityId"));
        ConsultingRoom c = consultingRoomService.findById(consultingRoomInaccessibility.getConsultingRoomId()).orElseGet(null);
        return new ConsultingRoomInaccessibilityWthConsultingRoom(consultingRoomInaccessibility, c);
    }


    public List<ConsultingRoomInaccessibility> findAllByConsultingRoomId(long id) {
        return repository.findAllByConsultingRoomId(id);
    }
}
