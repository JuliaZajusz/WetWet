package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping("/all")
    List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

}
