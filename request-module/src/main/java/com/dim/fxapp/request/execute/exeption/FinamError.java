package com.dim.fxapp.request.execute.exeption;

/**
 * Created by dima on 14.12.17.
 */
public class FinamError  extends Exception{
    public FinamError() {
        super();
    }

    public FinamError (String message) {
        super(message);
    }

    public FinamError(Throwable cause){
        super(cause);
    }

    public FinamError (String mesage, Throwable cause){
        super(mesage,cause);
    }
}
