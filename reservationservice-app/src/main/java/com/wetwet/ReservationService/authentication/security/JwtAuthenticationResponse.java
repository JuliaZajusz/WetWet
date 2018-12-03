package com.wetwet.ReservationService.authentication.security;

import com.wetwet.ReservationService.database.Credentials;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Credentials authenticationVM;

    public JwtAuthenticationResponse(String accessToken, Credentials credentials) {
        this.accessToken = accessToken;
        this.authenticationVM = credentials;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Credentials getAuthenticationVM() {
        return authenticationVM;
    }

    public void setAuthenticationVM(Credentials authenticationVM) {
        this.authenticationVM = authenticationVM;
    }
}
