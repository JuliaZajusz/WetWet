package com.wetwet.ReservationService.service;


import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }


    public List<Appointment> getAppointments() {
        return repository.findAll();
    }

    public Appointment createAppointments(Appointment appointment) {
        return repository.save(appointment);
    }
}
