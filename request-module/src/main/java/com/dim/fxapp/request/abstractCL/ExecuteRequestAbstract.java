package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.execute.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dima on 02.12.17.
 */
/*
controller advice
 */
/*
* this is class is abstract, but can use builder pattern
*/

public abstract class ExecuteRequestAbstract <F extends FinancialEntity> {

    @Value("${currency.main}")
    protected String MAIN;
    /*@Value("${currency.latest}")
    protected String LATEST;
    @Value("${currency.symbols}")
    protected String SYMBOLS;
    @Value("${currency.base}")
    protected String BASE;
    @Value("${currency.historical}")
    protected String HISTORICAL;
    @Value("${currency.myappid}")
    protected String MYAPPID;
    @Value("${currency.timeseries}")
    protected String TIMESERIES;
    @Value("${currency.ohlc}")
    protected String OHLC;
    @Value("${currency.start}")
    protected String START;
    @Value("${currency.end}")
    protected String END;    */

    protected static CloseableHttpClient httpClient = HttpClients.createDefault();
    protected HttpGet httpGet;

    protected List<Currency> currencyList = Arrays.asList(Currency.values());
    protected List<F> financialEntities = new ArrayList<F>();

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
    public abstract  void addListToMap(Map<String,Object> mapsForParse);
    public abstract Map<String,Object> getServerResponse(List<String> strRequest);
    public abstract List<String> getStringRequest();
    public abstract List<String> getStringRequest (LocalDateTime... dateArray);
    public abstract Map<String,Object> getQuotes();
    public abstract Map<String,Object> getQuotes(LocalDateTime...dateArray);



    //public void addListToMap(Map<String,Object> mapsForParse){

        /*ratesMap = (Map<String, Double>) mapResp.get("rates");
        List<Response> listOfResponse = new ArrayList<Response>();
        for(Request request: listofRequest){
            if(ratesMap.containsKey(request.getRequestedName())){
                Response response = Response.builder()
                        .currencyName(request.getCurrencyName())
                        .price(ratesMap.get(request.getRequestedName()))
                        .date(request.getDate())
                        .build();
                listOfResponse.add(response);
            }
        }
        //TODO рассмотреть возможность убрать Response вообще убрать ))
        for(Response response: listOfResponse ){
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(response.getCurrencyName())
                    .price(response.getRightPrice())
                    .date(response.getDate())
                    .build();
            financialEntities.add(quotesLive);
        }
        mapResp.put("rates",financialEntities);
        return mapResp;*/
    //}
}
