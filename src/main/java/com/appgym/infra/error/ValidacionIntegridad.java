package com.appgym.infra.error;

public class ValidacionIntegridad extends RuntimeException {
    public ValidacionIntegridad(String s) {
        super(s);
    }

}
