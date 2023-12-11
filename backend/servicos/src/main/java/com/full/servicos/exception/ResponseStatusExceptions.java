package com.full.servicos.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ResponseStatusExceptions {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        String mensagemErro = ex.getMessage();
        HttpStatusCode codigoStatus = ex.getStatusCode();
        BadRequestException badRequestException = new BadRequestException(mensagemErro);

        return new ResponseEntity(badRequestException, codigoStatus);
    }
}
