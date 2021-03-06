package com.pcelta.foobar.entity.factory;

import java.util.Calendar;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardFactory {

    public static CreditCard createSimple() {
        CreditCard card = new CreditCard();
        card.setNumber("4444333322221111");
        card.setCvv("123");
        card.setValidity(Calendar.getInstance());
        card.setCustomerName("PCELTA RIBEIRO");
        card.setLimit(new Double(30.50));
        card.setDueAccount(Calendar.getInstance());
        card.setIsActive(true);
        card.setClosing(Calendar.getInstance());
        
        return card;
    }
}
