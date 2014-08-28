package com.pcelta.foobar.repository;

import javax.persistence.EntityTransaction;

import com.pcelta.foobar.entity.Transaction;

public class TransactionRepository extends AbstractRepository{

    public void persist(Transaction transaction) {
        EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
        entityTransaction.begin();
        this.getEntityManager().persist(transaction);
        entityTransaction.commit();
    }
}
