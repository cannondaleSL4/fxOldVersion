package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.execute.exeption.ServerRequestExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 02.12.17.
 */

public abstract class ExecuteRequestAbstract <F extends FinancialEntity, C extends Criteria> {


    protected static CloseableHttpClient httpClient;
    protected HttpGet httpGet;

    protected List<FinancialEntity> financialEntities;
    protected List<Request> listofRequest;

    protected Map<String, Object> mapResp = new HashMap<>(); // full response from server
    protected Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp

    protected ExecuteRequestAbstract(){
        httpClient = HttpClients.createDefault();
        financialEntities = new ArrayList<FinancialEntity>();
        listofRequest = new ArrayList<>();
    }

    public abstract F getQuote(C criteria);
    public abstract Map<String,Object> getQuotes(C criteria) throws ServerRequestExeption;

    protected abstract List<String> getStringRequest(C criteria);

    public void addListToMap(Map<String,Object> mapsForParse){
        ratesMap = (Map<String, Double>) mapsForParse.get("rates");
        final String base = (String) mapsForParse.get("base");

        ratesMap.forEach((V,K) ->{
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(V)
                    .base(base)
                    .price(K)
                    .date(LocalDateTime.of(LocalDate.parse((String)mapsForParse.get("date")), LocalTime.now()))
                    .build();
            financialEntities.add(quotesLive);
        });
        mapResp.remove("rates");
        mapResp.remove("base");
    }

    public Map<String,Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption {
        for(String str: strRequest){
            httpGet = new HttpGet(str);
            try(CloseableHttpResponse response =  httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                mapResp = new ObjectMapper().readValue(EntityUtils.toString(entity), HashMap.class);
                if(mapResp.containsKey("error")) throw new ServerRequestExeption((String)mapResp.get("error"));
                addListToMap(mapResp);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapResp.put("quotes",financialEntities);
        return mapResp;
    }

}
