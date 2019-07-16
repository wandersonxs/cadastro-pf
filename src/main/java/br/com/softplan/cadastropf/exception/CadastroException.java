package br.com.softplan.cadastropf.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * CadastroException é responsável por exceções de runtime.
 * 
 * @author Wanderson Xesquevixos
 *
 */
public class CadastroException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int httpErrorCode = 400; // HttpStatus.BAD_REQUEST
    private String errorCode = "error";

    private List<String> additionalMessages = new ArrayList<String>();
    private List<String> errorMessages = new ArrayList<String>();

    public CadastroException(String message) {
        super(message);
    }

    public CadastroException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public CadastroException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CadastroException(List<String> messages, String errorCode) {
        this.errorMessages = messages;
        this.errorCode = errorCode;
    }

    public CadastroException(String message, int httpErrorCode) {
        super(message);
        this.httpErrorCode = httpErrorCode;
    }

    public CadastroException(String message, int httpErrorCode, String errorCode) {
        super(message);
        this.httpErrorCode = httpErrorCode;
        this.errorCode = errorCode;
    }

    public CadastroException() {
        super();
    }

    public CadastroException(String message, Throwable cause) {
        super(message, cause);
    }

    public CadastroException(Throwable cause) {
        super(cause);
    }

    public int getHttpErrorCode() {
        return this.httpErrorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<String> getAdditionalMessages() {
        return this.additionalMessages;
    }

    public void addAdditionalInformation(String value) {
        this.additionalMessages.add(value);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
