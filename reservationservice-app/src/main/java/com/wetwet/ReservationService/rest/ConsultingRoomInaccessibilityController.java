package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.service.ConsultingRoomInaccessibilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/consultingRoomInnaccessibility")
public class ConsultingRoomInaccessibilityController {
    private final ConsultingRoomInaccessibilityService consultingRoomInaccessibilityService;

    public ConsultingRoomInaccessibilityController(ConsultingRoomInaccessibilityService consultingRoomInaccesabilityService) {
        this.consultingRoomInaccessibilityService = consultingRoomInaccesabilityService;
    }

//    @GetMapping("/all")
//    public List<ConsultingRoomInaccessibility> getConsultingRoomInaccesabilities() {
//        return consultingRoomInaccessibilityService.getConsultingRoomInaccesabilities();
//    }

    @GetMapping("/all")
    public List<ConsultingRoomInaccessibility> getAllInaccesabilities() {
        return consultingRoomInaccessibilityService.getInaccessibilities();
    }
//
//    @GetMapping(path = "/{id}")
//    @ResponseBody
//    public ConsultingRoomInaccessibility getInaccesability(@PathVariable Long id) {
//        return consultingRoomInaccessibilityService.getInaccessibilityById(id);
//    }
//
//    @PostMapping()
//    public ConsultingRoomInaccessibility createInaccesability(@Valid @RequestBody ConsultingRoomInaccessibility consultingRoomInaccessibility) {
//        ConsultingRoomInaccessibility inn = consultingRoomInaccessibilityService.createInaccessibility(consultingRoomInaccessibility);
//        return inn;
//    }
}
