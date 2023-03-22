package com.raphaelming.oasis.exception;


import lombok.Getter;

@Getter
public class ApiResponseException extends RuntimeException{
    private ExceptionEnum error;

    public ApiResponseException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
