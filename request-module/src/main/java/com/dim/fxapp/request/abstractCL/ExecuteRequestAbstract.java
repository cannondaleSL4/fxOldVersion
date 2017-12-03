package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.impl.Currency;
import com.dim.fxapp.request.execute.Request;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dima on 02.12.17.
 */

/*
* this is class is abstract, but can use builder pattern
*/

public abstract class ExecuteRequestAbstract <F extends FinancialEntity> {

    protected String mainEndPoint = "https://openexchangerates.org/api/";
    protected String myAppId = "app_id=fde14524a8444f7390f3b18580348cf9";

    protected String latestEndPoint = "latest.json";

    protected String historicalEndPoint = "historical/*.json";

    protected String timeSeriesEndPoint = "time-series.json";

    protected String ohlc = "ohlc.json";


    protected List<Currency> currencyList;
    protected List<Request> requestList = new LinkedList<Request>();
    protected Date date;
    protected Date from;
    protected Date to;


    protected static abstract class Builder <T extends ExecuteRequestAbstract,
            B extends Builder<T,B>> {

        private T obj;
        private B thisObj;

        List<Currency> currencyList;
        List<Request> requestList = new LinkedList<Request>();
        Date date;
        Date from;
        Date to;

        public Builder(){
            obj = createObj();
            thisObj = getThis();
        }

        public T build() {return obj;}
        protected abstract T createObj();
        protected abstract B getThis();

        public B setList(List<Currency> currencyList){
            this.currencyList = currencyList;
            return thisObj;
        }

        public B date (Date date){
            this.date = date;
            return thisObj;
        }

        public B dateFrom(Date from){
            this.from = from;
            return thisObj;
        }

        public B dateTo(Date to){
            this.to = to;
            return thisObj;
        }
    }

    protected ExecuteRequestAbstract(){

    }

    protected abstract F getQuote(String currencyName);
    protected abstract List<F> getQuotes();
    protected abstract List<F> getQuotes(List<String> currenciesNames);

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
