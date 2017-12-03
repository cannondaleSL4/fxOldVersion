package com.dim.fxapp.entity.enums;

/**
 * Created by dima on 04.12.17.
 */
public enum Period {
    MINUTE ("1m"),
    FIVEMINUTES ("5m"),
    FIVETEENMINUTES ("15m"),
    ONEHOUR("1h"),
    TWELVEHOURS ("12h"),
    DAY ("1d"),
    WEEK ("1w"),
    MONTH ("1mo");

    private final String period;

    private Period (final String period){
        this.period = period;
    }

    @Override
    public String toString(){
        return period;
    }
}
