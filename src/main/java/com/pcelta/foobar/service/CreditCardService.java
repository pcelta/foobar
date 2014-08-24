package com.pcelta.foobar.service;

import javax.persistence.EntityNotFoundException;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.repository.CreditCardRepository;
import com.pcelta.foobar.validate.CreditCardValidate;

public class CreditCardService {

    private CreditCardRepository repository;
    private CreditCardValidate validator;

    public void setRepository(CreditCardRepository repository) {
        this.repository = repository;
    }

    private CreditCardRepository getRepository() {
        if (this.repository == null) {
            this.repository = new CreditCardRepository();
        }

        return this.repository;
    }

    public CreditCardValidate getValidator() {
        if (this.validator == null) {
            this.validator = new CreditCardValidate();
        }

        return this.validator;
    }

    public Boolean hasSufficientBalance(CreditCard creditCard, Double amount) {
        try {
            CreditCard cardToBeValided = this.getRepository().findOneByNumber(creditCard.getNumber());
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

    public Boolean exists(CreditCard creditCard) {
        try {
            CreditCard cardExists = this.getRepository().findOneByNumber(creditCard.getNumber());

            return true;
        } catch(EntityNotFoundException e) {

            return false;
        }
    }

    public CreditCard getCreditCardByNumber(String number) {
        return this.getRepository().findOneByNumber(number);
    }
}
