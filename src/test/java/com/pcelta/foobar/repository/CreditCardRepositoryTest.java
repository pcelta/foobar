package com.pcelta.foobar.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.entity.factory.CreditCardFactory;

public class CreditCardRepositoryTest {

    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        this.entityManager = com.pcelta.foobar.factory.EntityManagerFactory.createEntityManager();
    }

    @Test(expected=NoResultException.class)
    public void testFindByNumberNoResults() {
        CreditCardRepository repository = new CreditCardRepository();
        CreditCard result = repository.findOneByNumber("4111411141114111");
    }

    @Test
    public void testFindByNumberOneResult() {
        CreditCard card = CreditCardFactory.createSimple();

        String creditCardNumber = card.getNumber();

        // inserting data
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(card);
        transaction.commit();

        CreditCardRepository repository = new CreditCardRepository();
        CreditCard result = repository.findOneByNumber(creditCardNumber);

        Assert.assertEquals(creditCardNumber, result.getNumber());
    }

    @Test
    public void testFindByNumberAndExpirationShouldReturnOneResult() {
        CreditCard card = CreditCardFactory.createSimple();

        String creditCardNumber = card.getNumber();

        // inserting data
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(card);
        transaction.commit();

        CreditCardRepository repository = new CreditCardRepository();
        CreditCard result = repository.findOneByNumberAndExpiration(creditCardNumber, card.getValidity());

        Assert.assertEquals(creditCardNumber, result.getNumber());
    }

    @After
    public void tearDown() throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        Query query = this.entityManager.createQuery("DELETE FROM CreditCard");
        query.executeUpdate();
        transaction.commit();
    }

}
