package com.example.note.error;

import com.example.note.enumernation.ResponseEnum;

public class ServiceException extends RuntimeException {

    private ResponseEnum responseEnum;

    public ServiceException(ResponseEnum responseEnum) {
        super(String.valueOf(responseEnum.getReturnMsg()));
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

}
