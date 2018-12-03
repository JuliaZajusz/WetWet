package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByLogin(String login);
}
