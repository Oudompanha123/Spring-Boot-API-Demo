package com.ppcb.cafemerchant.common.components.exception;

import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final StatusCode statusCode;
    private final String message;
    private final int code;

    public BusinessException(StatusCode statusCode, Object... args) {
        this.statusCode = statusCode;
        this.message = statusCode.formatMessage(args);
        this.code = statusCode.getCode();
    }
}
