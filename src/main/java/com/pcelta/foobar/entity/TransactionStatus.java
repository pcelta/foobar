package com.pcelta.foobar.entity;

import com.pcelta.foobar.exception.InvalidArgumentException;

public class TransactionStatus {

    public static Integer APPROVED        = 0;
    public static Integer DENIED          = 1;
    public static Integer INVALID_REQUEST = 2;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    private TransactionStatus(Integer status) {
        this.status = status;
    }

    public static TransactionStatus createDeniedStatus() {
        return new TransactionStatus(TransactionStatus.DENIED);
    }

    public static TransactionStatus createApprovedStatus() {
        return new TransactionStatus(TransactionStatus.APPROVED);
    }

    public static TransactionStatus createInvalidRequestStatus() {
        return new TransactionStatus(TransactionStatus.INVALID_REQUEST);
    }
}
