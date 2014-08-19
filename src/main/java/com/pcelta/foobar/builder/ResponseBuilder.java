package com.pcelta.foobar.builder;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.pcelta.foobar.entity.TransactionStatus;
import com.pcelta.foobar.exception.InvalidArgumentException;

public class ResponseBuilder {

    public static Integer APPROVED_CODE           = TransactionStatus.APPROVED;
    public static Integer DENIED_CODE             = TransactionStatus.DENIED;
    public static Integer INVALID_REQUEST_CODE    = 2;

    private static String APPROVED_MESSAGE        = "Transacao Aprovada";
    private static String DENIED_MESSAGE          = "Transacao Nao Aprovada" + 
                                                    "(o cartão não tem limite suficiente)";
    private static String INVALID_REQUEST_MESSAGE = "Requisicao Inválida";


    public static String build(Integer code) throws InvalidArgumentException{
        if (code.equals(ResponseBuilder.APPROVED_CODE)) {
            return ResponseBuilder.buildXml(ResponseBuilder.APPROVED_CODE, ResponseBuilder.APPROVED_MESSAGE);
        }

        if (code.equals(ResponseBuilder.DENIED_CODE)) {
            return ResponseBuilder.buildXml(ResponseBuilder.DENIED_CODE, ResponseBuilder.DENIED_MESSAGE);
        }

        if (code.equals(ResponseBuilder.INVALID_REQUEST_CODE)) {
            return ResponseBuilder.buildXml(ResponseBuilder.INVALID_REQUEST_CODE, ResponseBuilder.INVALID_REQUEST_MESSAGE);
        }

        throw new InvalidArgumentException();
    }

    private static String buildXml(Integer code, String message) {
        Element response = new Element("resposta");
        Element codeElement = new Element("codigoRetorno");
        Element messageElement = new Element("mensagem");

        codeElement.setText(code.toString());
        messageElement.setText(message);

        response.addContent(codeElement);
        response.addContent(messageElement);
        
        Document document = new Document();
        document.setRootElement(response);

        XMLOutputter outPutter = new XMLOutputter();

        return outPutter.outputString(document);
    }
}
