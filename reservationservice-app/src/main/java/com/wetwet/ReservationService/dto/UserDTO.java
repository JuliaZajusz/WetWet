package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.authentication.CredentialsDTO;
import com.wetwet.ReservationService.database.Employee;

public class UserDTO {
    private Employee employee;
    private CredentialsDTO credentials;

    public Employee getEmployee() {
        return employee;
    }

    public CredentialsDTO getCredentials() {
        return credentials;
    }
}
