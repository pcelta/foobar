package com.pcelta.foobar.validate;

import java.util.Calendar;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardValidate {

    public Boolean isValid(CreditCard creditCard) {
        
        Calendar expiration = creditCard.getValidity();

        Calendar currentDate = Calendar.getInstance();

        if (expiration.compareTo(currentDate) < 0) {
            return false;
        }

        return true;
    }
}
