package com.dim.fxapp.request;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 30.11.17.
 */
public abstract class RequestAbstractClass {
    protected String ACCESS_KEY = "96748e760f44176b0ff16b234e204ea1";
    protected String BASE_URL = "http://apilayer.net/api/";
    protected String ENDPOINT = "live";
    protected String SOURCES = "USD";
    protected JSONObject exchangeRates;
    protected String currencies="";
    protected List<String> listOfCurrencies = new ArrayList<String>();

    public RequestAbstractClass(List<String> listOfCurrencies){
        this.listOfCurrencies = listOfCurrencies;
        String prefix = "";
        StringBuilder tempcurrencies = new StringBuilder();
        for(String currensy: listOfCurrencies){
            tempcurrencies.append(prefix);
            prefix = ",";
            tempcurrencies.append(currensy);
        }
        currencies = tempcurrencies.toString();
    }

    public abstract Map<String,Object> getLiveQuotes();
}
