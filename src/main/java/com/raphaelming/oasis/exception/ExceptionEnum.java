package com.raphaelming.oasis.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "ER01", "잘못된 요청이에요."),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "ER02", "권한이 없어요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ER03", "서버에 문제가 있어요."),
    BIND_EXCEPTION(HttpStatus.BAD_REQUEST, "B01", "검색어를 입력해주세요");
    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
