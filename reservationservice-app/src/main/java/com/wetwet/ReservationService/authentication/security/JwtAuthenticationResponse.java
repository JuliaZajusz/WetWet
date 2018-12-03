package com.wetwet.ReservationService.authentication.security;

import com.wetwet.ReservationService.database.Employee;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    //    private AuthenticationVM authenticationVM;
    private Employee authenticationVM;

    public JwtAuthenticationResponse(String accessToken, Employee userVM) {
        this.accessToken = accessToken;
        this.authenticationVM = userVM;
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

    public Employee getAuthenticationVM() {
        return authenticationVM;
    }

    public void setAuthenticationVM(Employee authenticationVM) {
        this.authenticationVM = authenticationVM;
    }
}
