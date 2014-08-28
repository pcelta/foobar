package com.pcelta.foobar.service;

import javax.persistence.NoResultException;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.repository.CreditCardRepository;
import com.pcelta.foobar.validate.CreditCardValidate;

public class CreditCardService {

    private CreditCardRepository repository;
    private CreditCardValidate validator;
    private CreditCard currentPersistedCreditCard;

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
        } catch (NoResultException e) {

            return false;
        }
    }

    public Boolean exists(CreditCard creditCard) {
        try {
            this.getRepository().findOneByNumber(creditCard.getNumber());

            return true;
        } catch(NoResultException e) {

            return false;
        }
    }

    public CreditCard getCreditCardByNumber(String number) {
        return this.getRepository().findOneByNumber(number);
    }

    public void debitTransaction(Transaction transaction) {
        CreditCard transactionCreditCard = transaction.getCreditCard();
        CreditCard updatedCreditCard = this.getRepository().findOneByNumber(transactionCreditCard.getNumber());

        Double currentAvailableLimit = updatedCreditCard.getAvailableLimit();

        currentAvailableLimit -= transaction.getAmount();
        updatedCreditCard.setAvailableLimit(currentAvailableLimit);

        this.getRepository().persist(updatedCreditCard);
        transaction.setCreditCard(updatedCreditCard);
    }

    public Boolean validate(CreditCard creditCard) {

        if (!this.getValidator().isValid(creditCard)) {
            return false;
        }

        try {
            this.currentPersistedCreditCard = this.getRepository().findOneByNumber(creditCard.getNumber());

            if (!this.getValidator().isValidWithExistingCreditCard(this.currentPersistedCreditCard, creditCard)) {
                return false;
            }

            return true;
        } catch(NoResultException e) {

            return false;
        }
    }

    public Transaction decorateWithPersistedCreditCard(Transaction transaction) {
        transaction.setCreditCard(this.currentPersistedCreditCard);

        return transaction;
    }
}
