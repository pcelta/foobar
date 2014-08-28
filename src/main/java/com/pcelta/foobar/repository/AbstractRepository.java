package com.pcelta.foobar.repository;

import javax.persistence.EntityManager;

import com.pcelta.foobar.factory.EntityManagerFactory;

public class AbstractRepository {

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (this.entityManager == null) {
            this.entityManager = EntityManagerFactory.createEntityManager();
        }

        return this.entityManager;
    }
}
