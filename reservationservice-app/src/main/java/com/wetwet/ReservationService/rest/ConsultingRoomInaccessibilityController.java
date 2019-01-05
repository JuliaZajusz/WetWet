package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.dto.ConsultingRoomInaccessibilityWthConsultingRoom;
import com.wetwet.ReservationService.service.ConsultingRoomInaccessibilityService;
import com.wetwet.ReservationService.service.ConsultingRoomService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/consultingRoomInnaccessibility")
public class ConsultingRoomInaccessibilityController {
    private final ConsultingRoomInaccessibilityService consultingRoomInaccessibilityService;
    private final ConsultingRoomService consultingRoomService;

    public ConsultingRoomInaccessibilityController(ConsultingRoomInaccessibilityService consultingRoomInaccessibilityService, ConsultingRoomService consultingRoomService) {
        this.consultingRoomInaccessibilityService = consultingRoomInaccessibilityService;
        this.consultingRoomService = consultingRoomService;
    }

    @GetMapping("/all")
    public List<ConsultingRoomInaccessibilityWthConsultingRoom> getAllInaccesabilities() {
        return consultingRoomService.getInaccessibilities();
    }


    @GetMapping(path = "/{id}")
    @ResponseBody
    public ConsultingRoomInaccessibilityWthConsultingRoom getInaccesability(@PathVariable Long id) {
        return consultingRoomService.getInaccessibilityById(id);
    }

    @PostMapping()
    public ConsultingRoomInaccessibility createInaccesability(@Valid @RequestBody ConsultingRoomInaccessibility consultingRoomInaccessibility) {
        ConsultingRoomInaccessibility inn = consultingRoomInaccessibilityService.createInaccessibility(consultingRoomInaccessibility);
        return inn;
    }
}
