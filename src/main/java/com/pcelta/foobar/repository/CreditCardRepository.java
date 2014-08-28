package com.pcelta.foobar.repository;

import java.util.Calendar;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardRepository extends AbstractRepository {

    public CreditCard findOneByNumber(String creditCardNumber) throws NoResultException {
        Query query = this.getEntityManager().createQuery("from CreditCard cc where cc.number = :number");
        query.setParameter("number", creditCardNumber);

        return (CreditCard) query.getSingleResult();
    }

    public CreditCard findOneByNumberAndExpiration(String creditCardNumber, Calendar expiration) throws NoResultException {
        Query query = this.getEntityManager().createQuery("from CreditCard cc where cc.number = :number and cc.validity = :validity");
        query.setParameter("number", creditCardNumber);
        query.setParameter("validity", expiration);

        return (CreditCard) query.getSingleResult();
    }

    public void persist(CreditCard creditCard) {
        EntityTransaction transaction = this.getEntityManager().getTransaction();
        transaction.begin();
        this.getEntityManager().persist(creditCard);
        transaction.commit();
    }

}
