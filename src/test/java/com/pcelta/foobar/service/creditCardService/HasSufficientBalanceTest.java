package com.pcelta.foobar.service.creditCardService;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.repository.CreditCardRepository;
import com.pcelta.foobar.service.CreditCardService;

public class HasSufficientBalanceTest {
    @Test
    public void testHasSufficientBalanceShouldReturnFalseWhenCreditCardNotExists() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenThrow(NoResultException.class);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.hasSufficientBalance(transactionCard, 10.00);
        Assert.assertFalse(result);
    }

    @Test
    public void testHasSufficientBalanceShouldReturnFalseWhenAvailableLimitEqualToZero() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        CreditCard persistenceCard = new CreditCard();
        persistenceCard.setAvailableLimit(0.00);
        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenReturn(persistenceCard);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.hasSufficientBalance(transactionCard, 10.00);
        Assert.assertFalse(result);
    }

    @Test
    public void testHasSufficientBalanceShouldReturnFalseWhenAvailableLimitLessThanAmount() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        CreditCard persistenceCard = new CreditCard();
        persistenceCard.setAvailableLimit(9.99);
        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenReturn(persistenceCard);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.hasSufficientBalance(transactionCard, 10.00);
        Assert.assertFalse(result);
    }

    @Test
    public void testHasSufficientBalanceShouldReturnTrueWhenAvailableLimitGreaterThanAmount() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        CreditCard persistenceCard = new CreditCard();
        persistenceCard.setAvailableLimit(10.00);
        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenReturn(persistenceCard);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.hasSufficientBalance(transactionCard, 9.99);
        Assert.assertTrue(result);
    }

    @Test
    public void testHasSufficientBalanceShouldReturnTrueWhenAvailableLimitEqualToAmount() {

        CreditCard transactionCard = new CreditCard();
        transactionCard.setNumber("4111511161117111");

        CreditCardRepository mockedRepository = Mockito.mock(CreditCardRepository.class);

        CreditCard persistenceCard = new CreditCard();
        persistenceCard.setAvailableLimit(10.00);
        Mockito.when(mockedRepository.findOneByNumber(transactionCard.getNumber())).
                thenReturn(persistenceCard);

        CreditCardService service = new CreditCardService();
        service.setRepository(mockedRepository);
        Boolean result = service.hasSufficientBalance(transactionCard, 10.00);
        Assert.assertTrue(result);
    }
}
