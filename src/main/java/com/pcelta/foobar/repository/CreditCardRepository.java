package com.pcelta.foobar.repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardRepository extends AbstractRepository {

    public CreditCard findOneByNumber(String creditCardNumber) throws EntityNotFoundException {
        Query query = this.getEntityManager().createQuery("from CreditCard cc where cc.number = :number");
        query.setParameter("number", creditCardNumber);

        List<CreditCard> cards = query.getResultList();

        if (cards.size() > 0) {
            return cards.get(0);
        }

        throw new EntityNotFoundException();
    }

}
