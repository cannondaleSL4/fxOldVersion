package com.dim.fxapp.entity.enums;

/**
 * Created by dima on 02.12.17.
 */
public enum Currency {
    GBPAUD ("GBPAUD"),
    GBPJPY ("GBPJPY"),
    GBPNZD ("GBPNZD"),

    EURJPY ("EURJPY"),
    EURGBP ("EURGBP");

    /*EURUSD ("EURUSD"),
    GBPUSD ("GBPUSD"),
    USDJPY ("USDJPY"),
    AUDUSD ("AUDUSD"),
    NZDUSD ("NZDUSD"),
    USDCAD ("USDCAD");*/

    //XAUUSD ("XAUUSD"),
    //XAGUSD ("XAGUSD");

    private final String currency;

    private Currency (final String currency){
        this.currency = currency;
    }

    @Override
    public String toString(){
        return currency;
    }
}
