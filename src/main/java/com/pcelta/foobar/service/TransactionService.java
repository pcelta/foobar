package com.pcelta.foobar.service;

import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.entity.TransactionStatus;

public class TransactionService {

    private CreditCardService creditCardService;

    private CreditCardService getCreditCardService() {
        if (this.creditCardService == null) {
            this.creditCardService = new CreditCardService();
        }

        return this.creditCardService;
    }

    public TransactionStatus run(Transaction transaction) {
        if (this.getCreditCardService().hasSufficientBalance(transaction.getCreditCard(), transaction.getAmount())) {
            
        }
        return TransactionStatus.createDeniedStatus();
    }
}
