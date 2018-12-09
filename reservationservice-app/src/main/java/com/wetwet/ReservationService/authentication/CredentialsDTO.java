package com.wetwet.ReservationService.authentication;

import com.wetwet.ReservationService.database.Credentials;

public class CredentialsDTO {
    private String login;
    private String password;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CredentialsDTO(Credentials credentials) {
        this.login = credentials.getLogin();
        this.password = credentials.getPasswordHash();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
