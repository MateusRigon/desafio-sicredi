package com.desafio.desafioibm.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionHandler extends RuntimeException{

    private int errorCode;
    private String errorMessage;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public ExceptionHandler(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ExceptionHandler(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ExceptionHandler(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public ExceptionHandler(Throwable cause) {
        super(cause);
    }
}
