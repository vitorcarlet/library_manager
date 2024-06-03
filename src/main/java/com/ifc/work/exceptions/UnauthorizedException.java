package com.ifc.work.exceptions;

public class UnauthorizedException extends  RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
