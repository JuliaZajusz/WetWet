package com.wetwet.ReservationService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Collection;

@Component
public class EntityManagerUtils {

    @Autowired
    @Qualifier("mainEntityManager")
    private EntityManager mainDatabase;

    @Autowired
    @Qualifier("secondEntityManager")
    private EntityManager secondDatabase;

    @Autowired
    @Qualifier("thirdEntityManager")
    private EntityManager thirdDatabase;

    @Autowired
    @Qualifier("fourthEntityManager")
    private EntityManager fourthDatabase;

    public EntityManager getEm() {

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        System.out.println("\n*******************\n");
        System.out.println(authorities);
        System.out.println("*******************\n");
        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            System.out.println("fourthDatabase");
            return fourthDatabase;
        }
        if (authorities.contains(new SimpleGrantedAuthority("DOCTOR"))) {
            System.out.println("thirdDatabase");
            return thirdDatabase;
        }
        if (authorities.contains(new SimpleGrantedAuthority("RECEPTIONIST"))) {
            System.out.println("secondDatabase");
            return secondDatabase;
        }
        System.out.println("mainDatabase");
        return mainDatabase;
    }

    public JpaRepositoryFactory getMainDatabaseJpaFactory() {
        return new JpaRepositoryFactory(mainDatabase);
    }

    public JpaRepositoryFactory getSecondDatabaseJpaFactory() {
        return new JpaRepositoryFactory(secondDatabase);
    }

    public JpaRepositoryFactory getThirdDatabaseJpaFactory() {
        return new JpaRepositoryFactory(thirdDatabase);
    }

    public JpaRepositoryFactory getFourthDatabaseJpaFactory() {
        return new JpaRepositoryFactory(fourthDatabase);
    }

    public JpaRepositoryFactory getJpaFactory() {
        return new JpaRepositoryFactory(getEm());
    }

}
