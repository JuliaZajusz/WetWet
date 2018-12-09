package com.wetwet.ReservationService.authentication.security;

import com.wetwet.ReservationService.database.Credentials;
import com.wetwet.ReservationService.database.Employee;
import com.wetwet.ReservationService.database.Position;
import com.wetwet.ReservationService.repository.CredentialsRepository;
import com.wetwet.ReservationService.repository.EmployeeRepository;
import com.wetwet.ReservationService.repository.PositionRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//@Component("UserDetailsServiceImpl")
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private CredentialsRepository credentialsRepository;
    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;

    public UserDetailsServiceImpl(CredentialsRepository credentialsRepository, EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.credentialsRepository = credentialsRepository;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials applicationUser = credentialsRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Employee user = employeeRepository.getOne(applicationUser.getEmployeeId());
        if (user == null) {
            throw new UsernameNotFoundException(applicationUser.getEmployeeId().toString());
        }

        Position position = positionRepository.getOne(user.getPositionId());

//    List<WetWetGrantedAuthority> wetWetAuthorities = new ArrayList<WetWetGrantedAuthority>() {{
//    add(new WetWetGrantedAuthority(position));}};
////        List<String> wetWetAuthorities = new ArrayList<String>() {{
////    add("NONE");}};
//
//        Collection<GrantedAuthority> grantedAuthorities = wetWetAuthorities.stream()
//                .collect(Collectors.toCollection(() -> new ArrayList<>(wetWetAuthorities.size())));
//
//        System.out.println("\n###########################\n");
//        System.out.println(((ArrayList<GrantedAuthority>) grantedAuthorities).get(0));
//        System.out.println("\n###########################\n");
//        System.out.println(grantedAuthorities.size());
//        System.out.println("\n###########################\n");


//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        WetWetGrantedAuthority authority = new WetWetGrantedAuthority();
//        authorities.add(authority);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(position.getType().toUpperCase());
        authorities.add(authority);


        return new User(applicationUser.getLogin(), applicationUser.getPasswordHash(), authorities);
    }

}
