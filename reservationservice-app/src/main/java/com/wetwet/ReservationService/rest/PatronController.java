package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.dto.PatronWithPetsDTO;
import com.wetwet.ReservationService.service.PatronService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/patron")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/all")
    public List<Patron> getAllPatrons() {
        return patronService.getPatrons();
    }

    @GetMapping("/allWithDetails")
    public List<PatronWithPetsDTO> getAllPatronsWithDetails() {
        return patronService.getPatronsWithDetails();
    }


    @GetMapping(path = "/{id}")
    @ResponseBody
    public PatronWithPetsDTO getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PostMapping()
    public Patron createPatron(@Valid @RequestBody Patron patron) {
        Patron newPatron = patronService.createPatron(patron);
        return newPatron;
    }
}
