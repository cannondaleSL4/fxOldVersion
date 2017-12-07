package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.execute.Request;
import org.springframework.beans.factory.annotation.Value;

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
    protected Date date;
    protected Date from;
    protected Date to;


    protected static abstract class Builder <T extends ExecuteRequestAbstract, B extends Builder<T,B>> {

        private T obj;
        private B thisObj;

        //List<Currency> currencyList = new LinkedList<Currency>(Arrays.asList(Currency.values()));
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
            //this.currencyList = currencyList;
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

    public abstract F getQuote(String currencyName);
    public  abstract Map<String,Object> getQuotes();
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


/*
    Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
    String formattedDate = dateFormat.format(timeStampDate);
     */
