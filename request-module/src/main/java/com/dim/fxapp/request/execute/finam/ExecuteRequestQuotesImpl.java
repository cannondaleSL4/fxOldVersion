package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Created by dima on 29.11.17
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
        return null;
    }


    @Override
    public Quotes getQuote(String currencyName) {
        return null;
    }

    @Override
    public void addListToMap(Map<String, Object> mapsForParse) {

    }

    @Override
    public Map<String, Object> getServerResponse(List<String> strRequest) {
        return null;
    }

    @Override
    public List<String> getStringRequest(String param) {
        return null;
    }



    @Override
    public Map<String,Object> getQuotes() {
        return null;
    }

}
