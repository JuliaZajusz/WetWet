package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping()
    Appointment addAppointment(@Valid @RequestBody Appointment appointment) {
        return appointmentService.createAppointments(appointment);
    }

    @DeleteMapping("/{id}")
    void addAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/all")
    List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

}
