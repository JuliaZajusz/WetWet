package com.wetwet.ReservationService.authentication;

import com.wetwet.ReservationService.authentication.security.JwtAuthenticationResponse;
import com.wetwet.ReservationService.authentication.security.JwtTokenProvider;
import com.wetwet.ReservationService.database.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authorization")
public class AuthenticationController {
    @Autowired
    private AutenticationService autenticationService;
    //    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthenticationController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @PostMapping("/sign-up")
//    public ResponseEntity<?> signUp(@RequestBody Employee userVM) {
//        if(autenticationService.existsByUsername(userVM.getUserName())) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        userVM.setPassword(bCryptPasswordEncoder.encode(userVM.getPassword()));
//        autenticationService.createUser(userVM);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationVM userVM) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userVM.getUsername(),
                        userVM.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Employee loggedUser = autenticationService.getUserByUsername(userVM.getUsername()).orElseThrow(() -> new IllegalArgumentException());
        String jwt = tokenProvider.generateToken(authentication, loggedUser);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, loggedUser));
    }

}


