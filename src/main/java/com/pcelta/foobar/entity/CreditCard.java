package com.pcelta.foobar.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="number")
    private String number;

    @Column(name="cvv")
    private String cvv;

    @Column(name="validity")
    private Calendar validity;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="`limit`")
    private Double limit;

    @Column(name="available_limit")
    private Double availableLimit;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="due_account")
    private Calendar dueAccount;

    @Column(name="closing")
    private Calendar closing;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return this.cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Calendar getValidity() {
        return this.validity;
    }

    public void setValidity(Calendar validity) {
        this.validity = validity;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getLimit() {
        return this.limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
        this.availableLimit = limit;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Calendar getDueAccount() {
        return this.dueAccount;
    }

    public void setDueAccount(Calendar dueAccount) {
        this.dueAccount = dueAccount;
    }

    public Calendar getClosing() {
        return closing;
    }

    public void setClosing(Calendar closing) {
        this.closing = closing;
    }

    public Double getAvailableLimit() {
        return this.availableLimit;
    }

    public void setAvailableLimit(Double availableLimit) {
        this.availableLimit = availableLimit;
    }

}
