package com.wetwet.ReservationService.authentication;

import com.wetwet.ReservationService.authentication.security.JwtAuthenticationResponse;
import com.wetwet.ReservationService.authentication.security.JwtTokenProvider;
import com.wetwet.ReservationService.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthenticationController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        if (autenticationService.existsByLogin(userDTO.getCredentials().getLogin())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userDTO.getCredentials().setPassword(bCryptPasswordEncoder.encode(userDTO.getCredentials().getPassword()));
        autenticationService.createUser(userDTO.getCredentials(), userDTO.getEmployee());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CredentialsDTO credentialsDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentialsDTO.getLogin(),
                        credentialsDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO loggedUser = autenticationService.getUserDTOByLogin(credentialsDTO.getLogin());
        String jwt = tokenProvider.generateToken(authentication, loggedUser);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, loggedUser));
    }
}


