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

        Calendar validExpiration = Calendar.getInstance();
        validExpiration.add(Calendar.YEAR, 1);

        creditCard.setValidity(validExpiration);
        creditCard.setNumber("1234123412341234");
        Boolean result = validator.isValid(creditCard);

        Assert.assertTrue(result);
    }

    @Test
    public void testValidateShouldReturnFalseWhenCardNumberInvalid() {
        CreditCardValidate validator = new CreditCardValidate();
        
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("xxx");
        Calendar validExpiration = Calendar.getInstance();
        validExpiration.add(Calendar.YEAR, 1);
        creditCard.setValidity(validExpiration);

        Boolean result = validator.isValid(creditCard);

        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldReturnFalseWhenCardNumberNotDefined() {
        CreditCardValidate validator = new CreditCardValidate();
        
        CreditCard creditCard = new CreditCard();
        Calendar validExpiration = Calendar.getInstance();
        validExpiration.add(Calendar.YEAR, 1);
        creditCard.setValidity(validExpiration);

        Boolean result = validator.isValid(creditCard);

        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldReturnFalseWhenValidityNotDefined() {
        CreditCardValidate validator = new CreditCardValidate();
        
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("xxx");

        Boolean result = validator.isValid(creditCard);

        Assert.assertFalse(result);
    }
}
