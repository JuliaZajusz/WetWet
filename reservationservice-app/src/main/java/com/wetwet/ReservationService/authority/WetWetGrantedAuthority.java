package com.wetwet.ReservationService.authority;

import com.wetwet.ReservationService.database.Position;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

public class WetWetGrantedAuthority implements GrantedAuthority {

    @NotNull
    private AuthorityRole role;

    public WetWetGrantedAuthority(Position position) {
        System.out.println("\n" + AuthorityRole.valueOf(position.getType().toUpperCase()) + "\n");
        this.role = AuthorityRole.valueOf(position.getType().toUpperCase());
    }

    public WetWetGrantedAuthority(@NotNull AuthorityRole role) {
        this.role = role;
    }

    public WetWetGrantedAuthority() {
        this.role = AuthorityRole.NONE;
    }


    @Override
    public String getAuthority() {
        System.out.println("\n//////////////////////////////////////////////\n");
        System.out.println(role.name());
        System.out.println("\n//////////////////////////////////////////////\n");
        return role.name();
    }
}

