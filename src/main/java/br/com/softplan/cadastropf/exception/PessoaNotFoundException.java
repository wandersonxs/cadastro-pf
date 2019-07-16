package br.com.softplan.cadastropf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PessoaNotFoundException Handler.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(String message){
        super(message);
    }
}
