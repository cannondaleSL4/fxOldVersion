package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.*;

/**
 * Created by dima on 02.12.17.
 */

public abstract class ExecuteRequestAbstract <F extends FinancialEntity, C extends Criteria> {


    protected static CloseableHttpClient httpClient;
    protected HttpGet httpGet;

    protected List<F> financialEntities;
    protected List<Request> listofRequest;

    protected Map<String, Object> mapResp = new HashMap<>(); // full response from server
    protected Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp

    protected ExecuteRequestAbstract(){
        httpClient = HttpClients.createDefault();
        financialEntities = new ArrayList<F>();
        listofRequest = new ArrayList<>();
    }

    /*public abstract F getQuote(Criteria criteria);
    public abstract  void addListToMap(Map<String,Object> mapsForParse);
    public abstract Map<String,Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption;
    public abstract Map<String,Object> getQuotes() throws ServerRequestExeption;
    public abstract Map<String,Object> getQuotes(LocalDateTime...dateArray) throws ServerRequestDateExeption, ServerRequestExeption;*/

    public abstract F getQuote(Criteria criteria);
    //public abstract Map<String,Object> getQuotes() throws ServerRequestExeption;
    public abstract Map<String,Object> getQuotes(Criteria criteria) throws ServerRequestExeption;

    protected abstract List<String> getStringRequest(Criteria criteria);
    protected abstract  void addListToMap(Map<String,Object> mapsForParse);
    protected abstract Map<String,Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption;

}
