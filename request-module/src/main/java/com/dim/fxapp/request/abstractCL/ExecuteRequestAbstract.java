package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.execute.Request;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by dima on 02.12.17.
 */

/*
* this is class is abstract, but can use builder pattern
*/

public abstract class ExecuteRequestAbstract <F extends FinancialEntity> {

    @Value("${currency.main}")
    protected String MAIN;
    @Value("${currency.myappid}")
    protected String MYAPPID;
    @Value("${currency.latest}")
    protected String LATEST;
    @Value("${currency.historical}")
    protected String HISTORICAL;
    @Value("${currency.timeseries}")
    protected String TIMESERIES;
    @Value("${currency.ohlc}")
    protected String OHLC;
    @Value("${currency.symbols}")
    protected String SYMBOLS;
    @Value("${currency.base}")
    protected String BASE;

    protected List<Currency> currencyList = Arrays.asList(Currency.values());
    protected List<FinancialEntity> financialEntities = new ArrayList<FinancialEntity>();
    //protected final String mapKey = "finEntity";

    protected List<Request> requestList = new LinkedList<Request>();
    protected LocalDate date;
    protected LocalDate from;
    protected LocalDate to;


    protected static abstract class Builder <T extends ExecuteRequestAbstract, B extends Builder<T,B>> {

        private T obj;
        private B thisObj;

        //List<Currency> currencyList = new LinkedList<Currency>(Arrays.asList(Currency.values()));
        List<Request> requestList = new LinkedList<Request>();
        LocalDate date;
        LocalDate from;
        LocalDate to;

        public Builder(){
            obj = createObj();
            thisObj = getThis();
        }

        public T build() {return obj;}
        protected abstract T createObj();
        protected abstract B getThis();

        public B setList(List<Currency> currencyList){
            //this.currencyList = currencyList;
            return thisObj;
        }

        public B date (LocalDate date){
            this.date = date;
            return thisObj;
        }

        public B dateFrom(LocalDate from){
            this.from = from;
            return thisObj;
        }

        public B dateTo(LocalDate to){
            this.to = to;
            return thisObj;
        }
    }

    protected ExecuteRequestAbstract(){

    }

    public abstract F getQuote(String currencyName);
    public  abstract Map<String,Object> getQuotes();
    public  abstract Map<String,Object> getQuotes(LocalDate...date);
    public abstract Map<String,Object> getQuotes(List<String> currenciesNames);

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
