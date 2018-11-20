package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Patron;
import com.wetwet.ReservationService.service.PatronService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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


    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        if (patron.isPresent()) {
            return new ResponseEntity<>(patron.get(), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public Patron createPatron(@Valid @RequestBody Patron patron) {
        Patron newPatron = patronService.createPatron(patron);
        return newPatron;
    }
}
