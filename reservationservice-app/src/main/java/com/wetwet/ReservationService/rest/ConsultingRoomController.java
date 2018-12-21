package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.service.ConsultingRoomService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consultingRoom")
class ConsultingRoomController {
    private final ConsultingRoomService consultingRoomService;

    public ConsultingRoomController(ConsultingRoomService consultingRoomService) {
        this.consultingRoomService = consultingRoomService;
    }

    @PostMapping()
    ConsultingRoom addConsultingRoom(@Valid @RequestBody ConsultingRoom consultingRoom) {
        return consultingRoomService.createConsultingRoom(consultingRoom);
    }

    @GetMapping("/all")
    List<ConsultingRoom> getConsultingRooms() {
        return consultingRoomService.getConsultingRooms();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ConsultingRoom getInaccesability(@PathVariable Long id) {
        return consultingRoomService.getConsultingRoomById(id);
    }
}
