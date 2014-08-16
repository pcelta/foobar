package com.pcelta.foobar.repository;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Before;
import org.junit.Test;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardRepositoryTest {

	private EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("foobar");
		this.entityManager = factory.createEntityManager();		
	}

	@Test
	public void testFindByNumberNoResults() {

		CreditCardRepository repository = new CreditCardRepository(this.entityManager);
		CreditCard result = repository.findOneByNumber("4111411141114111");

		Assert.assertNull(result.getId());
	}

	@Test
	public void testFindByNumberOneResult() {

		String creditCardNumber = "4444333322221111";

		CreditCard card = new CreditCard();
		card.setNumber(creditCardNumber);
		card.setCvv("123");
		card.setValidity(new Date());
		card.setCustomerName("PCELTA RIBEIRO");
		card.setLimit(new Double(30.50));
		
		card.setDueAccount(new Date());
		card.setIsActive(true);
		
		
		card.setClosing(new Date());

		// inserting data
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(card);
		transaction.commit();


		CreditCardRepository repository = new CreditCardRepository(this.entityManager);
		CreditCard result = repository.findOneByNumber(creditCardNumber);

		Assert.assertEquals(creditCardNumber, result.getNumber());

	}

	/*
	@After
	public void tearDown() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("foobar");
		this.entityManager = factory.createEntityManager();		
	} */

}
