package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private AppointmentRepository repository;
    @Autowired
    private EntityManagerUtils emUtils;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }


    public List<Appointment> getAppointments() {
        repository = emUtils.getJpaFactory().getRepository(AppointmentRepository.class);
        return repository.findAll();
    }
}
