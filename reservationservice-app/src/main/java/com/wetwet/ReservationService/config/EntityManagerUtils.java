package com.wetwet.ReservationService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

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

    public EntityManager getEm(String url) {

        System.out.println("\n*******************\n");
        System.out.println(url);
        System.out.println("*******************\n");
        if (url.contains("main"))
            return mainDatabase;
        if (url.contains("second"))
            return secondDatabase;
        if (url.contains("third"))
            return thirdDatabase;
        return fourthDatabase;
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

    public JpaRepositoryFactory getJpaFactory(String url) {
        return new JpaRepositoryFactory(getEm(url));
    }

}
