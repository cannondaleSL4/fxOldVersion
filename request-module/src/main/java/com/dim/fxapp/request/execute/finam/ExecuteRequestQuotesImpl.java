package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Created by dima on 29.11.17
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Value("${currency.mainfinam}")
    String MAIN;
    @Value("${currency.mainfinamrequest}")
    String MAIN_FOR_REQUEST;

    private Map<String, Object> mapResp = new HashMap<>(); // full response from server
    private Map<String,Double> ratesMap = new HashMap<>(); // only rates map from mapResp


    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) throws ServerRequestDateExeption, ServerRequestExeption {
        if(dateArray.length != 2) throw new ServerRequestDateExeption("incorrect date settings please check request format");
        from = dateArray[0];
        to = dateArray[1];
        mapResp = getServerResponse(getStringRequest("latest"));
        return mapResp;
    }

    @Override
    public List<String> getStringRequest(String param) {
        List<String> listOfStringRequest = new ArrayList<>();
        Map<String,StringBuilder> temporaryMap = new HashMap<>();

        currencyList.forEach(K -> listofRequest.add(
                Request.builder()
                        .currencyName(K.toString())
                        .baseCurrency( K.toString().substring(0,3))
                        .quoteCurrency( K.toString().substring(3))
                        .build()));

        listofRequest.forEach(K -> K.identifyBase());

        Collections.sort(listofRequest);

        String tempBase = listofRequest.get(0).getBase();
        temporaryMap.put(tempBase,new StringBuilder());



        return listOfStringRequest;
    }


    @Override
    public Quotes getQuote(String currencyName) {
        return null;
    }

    @Override
    public void addListToMap(Map<String, Object> mapsForParse) {

    }

    @Override
    public Map<String, Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption {
        return null;
    }

    @Override
    public Map<String, Object> getQuotes() throws ServerRequestExeption {
        return null;
    }
}
