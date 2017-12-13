package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
@Service("LiveQuotes")
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive> {

    @Value("${currency.main}")
    protected String MAIN;

    private Map<String, Object> mapResp = new HashMap<>(); // full response from server
    private Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp

    @Override
    public Map<String,Object> getQuotes() throws ServerRequestExeption {
        mapResp = getServerResponse(getStringRequest("latest"));
        return mapResp;
    }

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) throws ServerRequestDateExeption, ServerRequestExeption {
        if (dateArray.length != 1)throw new ServerRequestDateExeption("incorrect date settings please check request format");
        date = dateArray[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        mapResp = getServerResponse(getStringRequest(date.format(formatter)));
        return mapResp;
    }

    @Override
    public List<String> getStringRequest(String param){
        List<String> listOfStringRequest = new ArrayList<>();
        Map<String,StringBuilder> temporaryMap = new HashMap<>();

        currencyList.forEach(K -> listofRequest.add(
                Request.builder()
                        .currencyName(K)
                        .baseCurrency( K.substring(0,3))
                        .quoteCurrency( K.substring(3))
                        .build()));

        listofRequest.forEach(K -> K.identifyBase());

        Collections.sort(listofRequest);

        String tempBase = listofRequest.get(0).getBase();
        temporaryMap.put(tempBase,new StringBuilder());


        for(Request request: listofRequest){
            if(request.getBase().equals(tempBase)){
                temporaryMap.put(tempBase,temporaryMap.get(tempBase).append(request.getRequestedName() + ","));
            }else{
                tempBase = request.getBase();
                temporaryMap.put(tempBase,new StringBuilder().append(request.getRequestedName() + ","));
            }
        }

        temporaryMap.forEach((K,V)-> {
            listOfStringRequest.add(String.format(MAIN,param,K,V.deleteCharAt(V.length()-1)));
        });

        return listOfStringRequest;
    }

    @Override
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

    @Override
    public void addListToMap(Map<String,Object> mapsForParse){
        ratesMap = (Map<String, Double>) mapsForParse.get("rates");
        final String base = (String) mapsForParse.get("base");

        ratesMap.forEach((V,K) ->{
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(V)
                    .base(base)
                    .price(K)
                    .date(date)
                    .build();
            financialEntities.add(quotesLive);
        });
        mapResp.remove("rates");
        mapResp.remove("base");
    }

    @Override
    public QuotesLive getQuote(String currencyName) {
        return null;
    }
}