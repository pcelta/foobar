package com.pcelta.foobar.service;

import javax.persistence.EntityNotFoundException;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.repository.CreditCardRepository;

public class CreditCardService {

    private CreditCardRepository repository;

    private CreditCardRepository getRepository() {
        if (this.repository == null) {
            this.repository = new CreditCardRepository();
        }

        return this.repository;
    }

    public Boolean hasSufficientBalance(CreditCard creditCard, Double amount) {
        try {
            CreditCard cardToBeValided = this.repository.findOneByNumber(creditCard.getNumber());
            if (cardToBeValided.getAvailableLimit() <= 0) {
                return false;
            }

            if (cardToBeValided.getAvailableLimit() < amount) {
                return false;
            }

            return true;
        } catch (EntityNotFoundException e) {

            return false;
        }
    }

    public void setRepository(CreditCardRepository repository) {
        this.repository = repository;
    }
}
