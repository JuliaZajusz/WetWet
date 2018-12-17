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
//    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
    public PatronWithPetsDTO getPatronById(@PathVariable Long id) {
//        Optional<PatronWithPetsDTO> patron = patronService.getPatronById(id);
//        if (patron.isPresent()) {
//            return new ResponseEntity<>(patron.get(), null, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        return patronService.getPatronById(id);
    }

    @PostMapping()
    public Patron createPatron(@Valid @RequestBody Patron patron) {
        Patron newPatron = patronService.createPatron(patron);
        return newPatron;
    }
}
