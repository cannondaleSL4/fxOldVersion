package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 29.11.17.
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract {

    @Override
    public Quotes getQuote(String currencyName) {
        return null;
    }

    @Override
    public Map<String, Object> getServerResponse(String strRequest) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes() {
        return null;
    }

    @Override
    public Map<String, Object> getQuotes(LocalDate... date) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes(List currenciesNames) {
        return null;
    }
}
