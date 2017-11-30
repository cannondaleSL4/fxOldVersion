package com.dim.fxapp.entity.enums;

/**
 * Created by dima on 02.12.17.
 */
public enum Currency {
    EURUSD ("EURUSD"),
    GBPUSD ("GBPUSD"),
    JPYUSD ("JPYUSD"),
    AUDUSD ("AUDUSD"),
    NZDUSD ("NZDUSD"),
    CADUSD ("CADUSD"),


    GBPAUD ("GBPAUD"),
    GBPJPY ("GBPJPY"),
    GBPNZD ("GBPNZD"),

    XAUUSD ("XAUUSD"),
    XAGUSD ("XAGUSD");

    private final String currency;

    private Currency (final String currency){
        this.currency = currency;
    }

    @Override
    public String toString(){
        return currency;
    }
}
