package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.execute.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDateTime;
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
    @Value("${currency.start}")
    protected String START;
    @Value("${currency.end}")
    protected String END;

    protected static CloseableHttpClient httpClient = HttpClients.createDefault();
    protected HttpGet httpGet;

    protected List<Currency> currencyList = Arrays.asList(Currency.values());
    protected List<FinancialEntity> financialEntities = new ArrayList<FinancialEntity>();

    protected List<Request> requestList = new LinkedList<Request>();
    protected List<Request> listofRequest;
    protected LocalDateTime date;
    protected LocalDateTime from;
    protected LocalDateTime to;


    protected static abstract class Builder <T extends ExecuteRequestAbstract, B extends Builder<T,B>> {

        private T obj;
        private B thisObj;

        List<Request> requestList = new LinkedList<Request>();
        LocalDateTime date;
        LocalDateTime from;
        LocalDateTime to;

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

        public B date (LocalDateTime date){
            this.date = date;
            return thisObj;
        }

        public B dateFrom(LocalDateTime from){
            this.from = from;
            return thisObj;
        }

        public B dateTo(LocalDateTime to){
            this.to = to;
            return thisObj;
        }
    }

    protected ExecuteRequestAbstract(){

    }

    public abstract F getQuote(String currencyName);
    public abstract String getStringRequest (LocalDateTime... dateArray);
    public abstract Map<String,Object> getQuotes();
    public abstract Map<String,Object> getQuotes(LocalDateTime...dateArray);
    public abstract Map<String,Object> getQuotes(List<String> currenciesNames);

    public Map<String,Object> getServerResponse(String strRequest){
        Map<String, Object> local = new HashMap<String,Object>();
        httpGet = new HttpGet(strRequest);
        try(CloseableHttpResponse response =  httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            local = new ObjectMapper().readValue(EntityUtils.toString(entity), HashMap.class);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return local;
    }


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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
