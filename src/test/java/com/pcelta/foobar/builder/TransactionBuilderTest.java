package com.pcelta.foobar.builder;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.exception.InvalidArgumentException;

public class TransactionBuilderTest {

    @Test
    public void testBuildByRequestDataShouldReturnTransactionEntityWhenValidRequestData() throws InvalidArgumentException {
        String validRequestData = "<?xml version=\"1.0\" ?>" +
            "<requisicao>" +
            "<numeroCartao>1234567890987654</numeroCartao>" +
            "<dataExpiracao>05/12</dataExpiracao>" +
            "<valor>10.00</valor>" +
            "</requisicao>";
        
        Transaction result = TransactionBuilder.buildByRequestData(validRequestData);
        
        Assert.assertNotNull(result.getCreditCard());
        Assert.assertEquals("1234567890987654", result.getCreditCard().getNumber());
        Assert.assertEquals(04, result.getCreditCard().getValidity().get(Calendar.MONTH));
        Assert.assertEquals(2012, result.getCreditCard().getValidity().get(Calendar.YEAR));
        Assert.assertEquals(10.00, result.getAmount());
    }

    @Test(expected=InvalidArgumentException.class)
    public void testBuildByRequestDataThrowInvalidArgumentExceptionWhenInvalidRequestData() throws InvalidArgumentException {
        String validRequestData = "";

        Transaction result = TransactionBuilder.buildByRequestData(validRequestData);
    }
}
