package com.ifc.work.services;

import org.springframework.stereotype.Component;


public final class SingletonInformação {

    private static SingletonInformação instance;
    public String value;

    private SingletonInformação(String value) {
        this.value = value;
    }

    public static SingletonInformação getInstance(String value) {
        if (instance == null) {
            instance = new SingletonInformação(value);
        }
        return instance;
    }
}
