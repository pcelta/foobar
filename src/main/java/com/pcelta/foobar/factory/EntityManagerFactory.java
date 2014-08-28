package com.pcelta.foobar.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

    private static EntityManager entityManager;

    public static EntityManager createEntityManager() {
        if (EntityManagerFactory.entityManager == null) {
            javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory("foobar");
            EntityManagerFactory.entityManager = factory.createEntityManager();
        }

        return EntityManagerFactory.entityManager;
    }
}
