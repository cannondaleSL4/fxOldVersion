package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;

import java.util.List;

/**
 * Created by dima on 30.11.17.
 */
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract {

    @Override
    protected QuotesLive getQuote(String currencyName) {
        return null;
    }

    @Override
    protected List<QuotesLive> getQuotes() {
        return null;
    }

    @Override
    protected List<QuotesLive> getQuotes(List currenciesNames) {
        return null;
    }

}
