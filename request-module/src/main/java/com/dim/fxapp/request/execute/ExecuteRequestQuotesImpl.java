package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;

import java.util.List;

/**
 * Created by dima on 29.11.17.
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract {

    @Override
    protected Quotes getQuote(String currencyName) {
        return null;
    }

    @Override
    protected List <Quotes> getQuotes() {
        return null;
    }

    @Override
    protected List<Quotes> getQuotes(List currenciesNames) {
        return null;
    }
}