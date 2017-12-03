package com.dim.fxapp.request.exeption;

/**
 * Created by dima on 02.12.17.
 */
public class CurrencyRequestExeption extends Exception {
    public CurrencyRequestExeption() {
        super();
    }

    public CurrencyRequestExeption (String message) {
        super(message);
    }

    public CurrencyRequestExeption(Throwable cause){
        super(cause);
    }

    public CurrencyRequestExeption (String mesage, Throwable cause){
        super(mesage,cause);
    }
}
