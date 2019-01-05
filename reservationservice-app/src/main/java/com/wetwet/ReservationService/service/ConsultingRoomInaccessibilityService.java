package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.dto.ConsultingRoomInaccessibilityWthConsultingRoom;
import com.wetwet.ReservationService.repository.ConsultingRoomInaccessibilityRepository;
import com.wetwet.ReservationService.repository.ConsultingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultingRoomInaccessibilityService {
    @Autowired
    private ConsultingRoomInaccessibilityRepository consultingRoomInaccessibilityRepository;
    @Autowired
    private ConsultingRoomRepository consultingRoomRepository;

    public List<ConsultingRoomInaccessibilityWthConsultingRoom> getInaccessibilities() {
        List<ConsultingRoomInaccessibility> consultingRoomInaccessibilities = consultingRoomInaccessibilityRepository.findAll();
        List<ConsultingRoomInaccessibilityWthConsultingRoom> consultingRoomInaccessibilityWthConsultingRooms = consultingRoomInaccessibilities.stream()
                .map(consultingRoomInaccessibility ->
                        {
                            ConsultingRoom c = consultingRoomRepository.findById(consultingRoomInaccessibility.getConsultingRoomId()).orElseGet(null);
                            return new ConsultingRoomInaccessibilityWthConsultingRoom(consultingRoomInaccessibility, c);
                        }
                ).collect(Collectors.toList());
        return consultingRoomInaccessibilityWthConsultingRooms;
    }

    public ConsultingRoomInaccessibility createInaccessibility(ConsultingRoomInaccessibility consultingRoomInaccessibility) {
        return consultingRoomInaccessibilityRepository.save(consultingRoomInaccessibility);
    }

    public ConsultingRoomInaccessibilityWthConsultingRoom getInaccessibilityById(Long id) {
        ConsultingRoomInaccessibility consultingRoomInaccessibility = consultingRoomInaccessibilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("consultingRoomInaccessibilityId"));
        ConsultingRoom c = consultingRoomRepository.findById(consultingRoomInaccessibility.getConsultingRoomId()).orElseGet(null);
        return new ConsultingRoomInaccessibilityWthConsultingRoom(consultingRoomInaccessibility, c);
    }
}
