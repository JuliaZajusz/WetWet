package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.repository.ConsultingRoomInaccessibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConsultingRoomInaccessibilityService {
    @Autowired
    private ConsultingRoomInaccessibilityRepository repository;

    public ConsultingRoomInaccessibility createInaccessibility(ConsultingRoomInaccessibility consultingRoomInaccessibility) {
        return repository.save(consultingRoomInaccessibility);
    }

    public List<ConsultingRoomInaccessibility> findAllByConsultingRoomId(long id) {
        return repository.findAllByConsultingRoomId(id);
    }

    public List<ConsultingRoomInaccessibility> getConsultingRoomInaccessibilities() {
        return repository.findAll();
    }

    public ConsultingRoomInaccessibility getConsultingRoomInaccessibility(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("consultingRoomInaccessibilityId"));
    }
}
