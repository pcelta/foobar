package com.pcelta.foobar.builder;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.pcelta.foobar.entity.CreditCard;
import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.exception.InvalidArgumentException;

public class TransactionBuilder {

    private static int CURRENT_AGE         = 2000;
    private static int DIFF_CALENDAR_MONTH = 1;

    public static Transaction buildByRequestData(String requestData) throws InvalidArgumentException{
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document;
        try {
            document = saxBuilder.build(new StringReader(requestData));
            Element request = document.getRootElement();
            Element creditCardNumber = request.getChild("numeroCartao");
            Element creditCardValidity = request.getChild("dataExpiracao");
            Element transactionAmount = request.getChild("valor");
            
            CreditCard creditCard = new CreditCard();
            creditCard.setNumber(creditCardNumber.getText());
            
            Calendar validity = TransactionBuilder.buildCreditCardValidity(creditCardValidity);
            creditCard.setValidity(validity);
            
            Transaction transaction = new Transaction();
            transaction.setCreditCard(creditCard);
            transaction.setAmount(Double.parseDouble(transactionAmount.getText()));
            
            return transaction;
        } catch (JDOMException | IOException e) {
            throw new InvalidArgumentException();
        }
        
    }
    
    private static Calendar buildCreditCardValidity(Element validityElement) {
        Calendar validity = Calendar.getInstance();

        String[] monthYear = validityElement.getText().split("/");
        validity.set(Calendar.MONTH, Integer.parseInt(monthYear[0]) - TransactionBuilder.DIFF_CALENDAR_MONTH);
        validity.set(Calendar.YEAR, TransactionBuilder.CURRENT_AGE + Integer.parseInt(monthYear[1]));

        return validity;
    }
}
