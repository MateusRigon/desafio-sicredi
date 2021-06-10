package com.desafio.desafioibm.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionController extends Exception{

    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionHandler.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Object handlerException(ExceptionHandler ex){
        String response = ex.getErrorMessage();
        System.out.println(response);
        return ex;
    }
}
