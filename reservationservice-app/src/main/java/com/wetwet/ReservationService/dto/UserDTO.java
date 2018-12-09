package com.wetwet.ReservationService.dto;

import com.wetwet.ReservationService.authentication.CredentialsDTO;
import com.wetwet.ReservationService.database.Employee;

public class UserDTO {
    private Employee employee;
    private CredentialsDTO credentialsDTO;

    public UserDTO() {
    }

    public UserDTO(CredentialsDTO credentialsDTO, Employee employee) {
        this.employee = employee;
        this.credentialsDTO = credentialsDTO;
    }

    public Employee getEmployee() {
        return employee;
    }

    public CredentialsDTO getCredentials() {
        return credentialsDTO;
    }
}
