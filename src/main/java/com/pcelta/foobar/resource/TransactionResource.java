package com.pcelta.foobar.resource;

import java.io.IOException;
import java.io.StringReader;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.pcelta.foobar.builder.ResponseBuilder;
import com.pcelta.foobar.builder.TransactionBuilder;
import com.pcelta.foobar.entity.Status;
import com.pcelta.foobar.entity.Transaction;
import com.pcelta.foobar.entity.TransactionStatus;
import com.pcelta.foobar.exception.InvalidArgumentException;
import com.pcelta.foobar.service.TransactionService;

@Path("autorizacao")
public class TransactionResource {
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String show(String requestData) throws InvalidArgumentException
    {
        try {
            Transaction transaction = TransactionBuilder.buildByRequestData(requestData);
            
            TransactionService service = new TransactionService();
            TransactionStatus transactionStatus = service.run(transaction);
            
            return ResponseBuilder.build(transactionStatus.getStatus());
        } catch (InvalidArgumentException e) {
            return ResponseBuilder.build(ResponseBuilder.INVALID_REQUEST_CODE);
        }
    }

}
