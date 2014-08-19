package com.pcelta.foobar.builder;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;

import com.pcelta.foobar.exception.InvalidArgumentException;

public class ResponseBuilderTest {

    @Test(expected=InvalidArgumentException.class)
    public void testBuildShouldThrowInvalidArgumentExceptionWhenInvalidType() throws InvalidArgumentException {
        ResponseBuilder.build(8);
    }

    @Test
    public void testBuildShouldReturnApprovedResponseWhenTypeEqualToApproved() throws InvalidArgumentException, JDOMException, IOException {
        String result = ResponseBuilder.build(ResponseBuilder.APPROVED_CODE);

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new StringReader(result));
        Element xml = document.getRootElement();

        Element codeElment = xml.getChild("codigoRetorno");
        Assert.assertEquals("0", codeElment.getText());

        Element messageElement = xml.getChild("mensagem");
        Assert.assertEquals("Transacao Aprovada", messageElement.getText());
    }

    @Test
    public void testBuildShouldReturnDeniedResponseWhenTypeEqualToDenied() throws InvalidArgumentException, JDOMException, IOException {
        String result = ResponseBuilder.build(ResponseBuilder.DENIED_CODE);

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new StringReader(result));
        Element xml = document.getRootElement();

        Element codeElment = xml.getChild("codigoRetorno");
        Assert.assertEquals("1", codeElment.getText());

        Element messageElement = xml.getChild("mensagem");
        String expectedMessage = "Transacao Nao Aprovada" + 
                                 "(o cartão não tem limite suficiente)";
        Assert.assertEquals(expectedMessage, messageElement.getText());
    }

    @Test
    public void testBuildShouldReturnInvalidRequestResponseWhenTypeEqualToInvalidRequest() throws InvalidArgumentException, JDOMException, IOException {
        String result = ResponseBuilder.build(ResponseBuilder.INVALID_REQUEST_CODE);

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new StringReader(result));
        Element xml = document.getRootElement();

        Element codeElment = xml.getChild("codigoRetorno");
        Assert.assertEquals("2", codeElment.getText());

        Element messageElement = xml.getChild("mensagem");
        Assert.assertEquals("Requisicao Inválida", messageElement.getText());
    }
}
