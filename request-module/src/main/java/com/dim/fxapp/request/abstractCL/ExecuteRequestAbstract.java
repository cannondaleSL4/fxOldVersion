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

    protected static CloseableHttpClient httpClient;
    protected HttpGet httpGet;
    protected List<Currency> currencyList = Arrays.asList(Currency.values());
    protected List<F> financialEntities;

    protected List<Request> listofRequest;
    protected LocalDateTime date;
    protected LocalDateTime from;
    protected LocalDateTime to;

    private Map<String, Object> mapResp = new HashMap<>(); // full response from server
    private Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp

    protected ExecuteRequestAbstract(){
        httpClient = HttpClients.createDefault();
        currencyList = Arrays.asList(Currency.values());
        financialEntities = new ArrayList<F>();
        listofRequest = new ArrayList<>();
    }
    public abstract F getQuote(String currencyName);
    public abstract  void addListToMap(Map<String,Object> mapsForParse);
    public abstract Map<String,Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption;
    public abstract List<String> getStringRequest(String param);
    public abstract Map<String,Object> getQuotes() throws ServerRequestExeption;
    public abstract Map<String,Object> getQuotes(LocalDateTime...dateArray) throws ServerRequestDateExeption, ServerRequestExeption;
}
