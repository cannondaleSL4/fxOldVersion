package com.dim.fxapp.request.execute;

import com.dim.fxapp.request.RequestAbstractClass;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 30.11.17.
 */
public class RequestStockExecute extends RequestAbstractClass {

    public RequestStockExecute(List<String> listOfCurrencies){
        super(listOfCurrencies);
    }

    @Override
    public Map<String, Object> getLiveQuotes() {
        return null;
    }
}
