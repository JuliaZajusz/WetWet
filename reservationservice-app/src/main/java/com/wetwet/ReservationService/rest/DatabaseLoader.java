package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseLoader implements CommandLineRunner{
    private final PatientsRepository repository;

    @Autowired
    public DatabaseLoader(PatientsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Patient("Frodo", "F", "2010-10-10", "coat", "special", 4));
    }
}
