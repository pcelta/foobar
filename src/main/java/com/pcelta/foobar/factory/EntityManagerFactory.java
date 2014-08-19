package com.pcelta.foobar.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	public static EntityManager createEntityManager() {
		javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory("foobar");
		
		return factory.createEntityManager();
	}
}
