package com.pcelta.foobar.validate;

import java.util.Calendar;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardValidate {

    private static int CREDIT_CARD_NUMBER_LENGHT = 16;
    
    public Boolean isValid(CreditCard creditCard) {
        
        if (creditCard.getValidity() == null) {
            return false;
        }
        
        Calendar expiration = creditCard.getValidity();

        Calendar currentDate = Calendar.getInstance();

        if (expiration.compareTo(currentDate) < 0) {
            return false;
        }

        if (creditCard.getNumber() == null) {
            return false;
        }
        
        if (creditCard.getNumber().length() != CreditCardValidate.CREDIT_CARD_NUMBER_LENGHT) {
            return false;
        }

        return true;
    }
}
