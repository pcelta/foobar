package com.pcelta.foobar.validate;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardValidateTest {

    @Test
    public void testValidateShouldReturnFalseWhenExpirationDateInvalid() {
        CreditCardValidate validator = new CreditCardValidate();
        
        CreditCard creditCard = new CreditCard();

        Calendar invalidExpiration = Calendar.getInstance();
        invalidExpiration.set(2010, 1, 1);

        creditCard.setValidity(invalidExpiration);
        Boolean result = validator.isValid(creditCard);

        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldReturnTrueWhenExpirationDateValid() {
        CreditCardValidate validator = new CreditCardValidate();
        
        CreditCard creditCard = new CreditCard();

        Calendar invalidExpiration = Calendar.getInstance();
        invalidExpiration.add(Calendar.YEAR, 1);

        creditCard.setValidity(invalidExpiration);
        Boolean result = validator.isValid(creditCard);

        Assert.assertTrue(result);
    }
}
