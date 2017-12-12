package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

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

    //protected List<Request> requestList = new LinkedList<Request>();
    protected List<Request> listofRequest = new ArrayList<>();
    protected LocalDateTime date;
    protected LocalDateTime from;
    protected LocalDateTime to;

    private Map<String, Object> mapResp = new HashMap<>(); // full response from server
    private Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp

    protected static abstract class Builder <T extends ExecuteRequestAbstract, B extends Builder<T,B>> {

        private T obj;
        private B thisObj;

        List<Request> requestList = new LinkedList<Request>();
        LocalDateTime date = LocalDateTime.now();
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
    public abstract Map<String,Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption;
    public abstract List<String> getStringRequest(String param);
    public abstract Map<String,Object> getQuotes() throws ServerRequestExeption;
    public abstract Map<String,Object> getQuotes(LocalDateTime...dateArray) throws ServerRequestDateExeption, ServerRequestExeption;
}
