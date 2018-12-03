package com.wetwet.ReservationService.repository;

import com.wetwet.ReservationService.database.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
}
