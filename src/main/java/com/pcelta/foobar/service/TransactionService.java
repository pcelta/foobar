package com.pcelta.foobar.service;

import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.entity.TransactionStatus;

public class TransactionService {

    public TransactionStatus run(Transaction transaction) {
        return TransactionStatus.createDeniedStatus();
    }
}
