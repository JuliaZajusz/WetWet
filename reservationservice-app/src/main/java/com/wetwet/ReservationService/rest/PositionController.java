package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Position;
import com.wetwet.ReservationService.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/position")
class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/all")
    List<Position> getAllPositions() {
        return positionService.getPositions();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Optional<Position> position = positionService.getPositionById(id);
        if (position.isPresent()) {
            return new ResponseEntity<>(position.get(), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public Position createPosition(@Valid @RequestBody Position position) {
        return positionService.createPosition(position);
    }

}
