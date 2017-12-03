package com.dim.fxapp.request;

import com.dim.fxapp.entity.FinancialEntity;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 30.11.17.
 */
public abstract class TempSecond {
    protected String ACCESS_KEY = "96748e760f44176b0ff16b234e204ea1";
    protected String BASE_URL = "http://apilayer.net/api/";
    protected String ENDPOINT = "live";
    protected String SOURCES = "USD";
    protected JSONObject exchangeRates;
    protected String currencies="";
    protected List<String> listOfCurrencies = new ArrayList<String>();
    protected List<FinancialEntity> listOfResponse = new ArrayList<FinancialEntity>();
    protected List<List<String>> listOfListOfCurrencies = new ArrayList<>();


    public TempSecond(List<String> listOfCurrencies){

        /*Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.contains("USD") &&
                        !o2.contains("USD")){
                    return -1;
                }

                if (!o1.contains("USD") &&
                        o2.contains("USD")){
                    return 1;
                }

                return 0;
            }
        };

        this.listOfCurrencies = listOfCurrencies;
        Collections.sort(listOfCurrencies,cmp);*/

        for(String str : listOfCurrencies){
            if (str.contains("USD")){
                listOfListOfCurrencies.get(0).add(str);
                listOfCurrencies.remove(str);
            }

        }

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
