package com.pcelta.foobar.service;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.entity.TransactionStatus;
import com.pcelta.foobar.repository.TransactionRepository;
import com.pcelta.foobar.validate.CreditCardValidate;

public class TransactionService {

    private TransactionRepository repository;
    private CreditCardService creditCardService;
    private CreditCardValidate creditCardValidator;

    private TransactionRepository getRepository() {
        if (this.repository == null) {
            this.repository = new TransactionRepository();
        }

        return this.repository;
    }

    private CreditCardValidate getCreditCardValidator() {
        if (this.creditCardValidator == null) {
            this.creditCardValidator = new CreditCardValidate();
        }

        return this.creditCardValidator;
    }

    private CreditCardService getCreditCardService() {
        if (this.creditCardService == null) {
            this.creditCardService = new CreditCardService();
        }

        return this.creditCardService;
    }

    public TransactionStatus perform(Transaction transaction) {

        if (!this.getCreditCardService().validate(transaction.getCreditCard())) {
            return TransactionStatus.createInvalidRequestStatus();
        }

        transaction = this.getCreditCardService().decorateWithPersistedCreditCard(transaction);

        if (!this.getCreditCardService().hasSufficientBalance(transaction.getCreditCard(), transaction.getAmount())) {
            return TransactionStatus.createDeniedStatus();
        }

       this.confirmTransaction(transaction);

        return TransactionStatus.createApprovedStatus();
    }

    private void confirmTransaction(Transaction transaction) {
        this.getCreditCardService().debitTransaction(transaction);
        this.getRepository().persist(transaction);
    }
}
