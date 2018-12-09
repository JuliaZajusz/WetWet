package com.wetwet.ReservationService.authentication.security;

import com.wetwet.ReservationService.database.Credentials;
import com.wetwet.ReservationService.repository.CredentialsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Collections.emptyList;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private CredentialsRepository credentialsRepository;

    public UserDetailsServiceImpl(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials applicationUser = credentialsRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(applicationUser.getLogin(), applicationUser.getPasswordHash(), emptyList());
    }
}
