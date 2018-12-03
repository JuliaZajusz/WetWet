package com.wetwet.ReservationService.authentication;

public class AuthenticationVM {
    private String username;
    private String password;

    public AuthenticationVM() {
    }

    public AuthenticationVM(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public AuthenticationVM(String username, Long id) {
//        this.username = username;
//        this.id = id;
//    }
//
//
//    public AuthenticationVM(Employee userVM) {
//        this.username = userVM.getFirstName();
//        this.id = userVM.getId();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
