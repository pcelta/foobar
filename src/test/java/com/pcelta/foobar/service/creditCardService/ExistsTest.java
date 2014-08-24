package com.pcelta.foobar.service.creditCardService;

import javax.persistence.EntityNotFoundException;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.repository.CreditCardRepository;
import com.pcelta.foobar.service.CreditCardService;

public class ExistsTest {

    @Test
    public void testExistsShouldReturnFalseWhenCreditCardNotExists() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenThrow(EntityNotFoundException.class);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.exists(transactionCard);
        Assert.assertFalse(result);
    }

    @Test
    public void testExistsShouldReturnTrueWhenCreditCardExists() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenReturn(new CreditCard());

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.exists(transactionCard);
        Assert.assertTrue(result);
    }
}
