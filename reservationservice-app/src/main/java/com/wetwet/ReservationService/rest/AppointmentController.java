package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Appointment;
import com.wetwet.ReservationService.dto.AppointmentWithPatientAndAddress;
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
    Appointment addAppointment(@Valid @RequestBody AppointmentWithPatientAndAddress appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    void addAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/{id}")
    AppointmentWithPatientAndAddress getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/all")
    List<AppointmentWithPatientAndAddress> getAppointments() {
        return appointmentService.getAppointments();
    }

    @GetMapping("/all/{patientId}")
    List<AppointmentWithPatientAndAddress> getPatientAppointments(@PathVariable Long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }

}
