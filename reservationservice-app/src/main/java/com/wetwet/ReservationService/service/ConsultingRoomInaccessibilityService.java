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

    public List<ConsultingRoomInaccessibility> getInaccessibilities() {
        return repository.findAll();
    }
}
