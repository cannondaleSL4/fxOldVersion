package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.exeption.CurrencyRequestExeption;
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
import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive> {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpGet httpGet;
    private Map<String, Object> mapResp; // full response from server
    private Map<String,Double> ratesMap; // only rates map from mapResp
    private List<Request> listofRequest;


    @Override
    public QuotesLive getQuote(String currencyName) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes() {
        String request = getStringRequest();
        mapResp = getServerResponse(request);
        return mapResp.containsKey("error") ? mapResp : parseResponse();
    }

    @Override
    public Map<String, Object> getQuotes(LocalDate... dateArray) {
        if (dateArray.length != 1){
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("error","incorrect date settings please check request format") ;
            return response;
        }
        String request = getStringRequest(dateArray[0]);

        mapResp = getServerResponse(request);

        return mapResp.containsKey("error") ? mapResp : parseResponse();
    }

    @Override
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


    @Override
    public Map<String, Object> getQuotes(List<String> currenciesNames) {
        return null;
    }

    public String getStringRequest(){
        StringBuilder result = new StringBuilder();
        result.append(MAIN + LATEST + MYAPPID + "&" +SYMBOLS + "=");
        listofRequest = new LinkedList<Request>();
        Request request;
        for(Currency currency : currencyList){
            request = Request.builder()
                    .currencyName(currency.toString())
                    .baseCurrency( currency.toString().substring(0,3))
                    .quoteCurrency( currency.toString().substring(3))
                    .build();
            request.identifyBase();
            listofRequest.add(request);
        }

        Collections.sort(listofRequest);

        for(Request requestInto : listofRequest){
            result.append(requestInto);
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }

    public String getStringRequest(LocalDate date){
        StringBuilder result = new StringBuilder();
        result.append(MAIN + HISTORICAL + date + ".json?" + MYAPPID + "&" + SYMBOLS + "=");
        listofRequest = new LinkedList<Request>();
        Request request;
        for(Currency currency : currencyList) {
            request = Request.builder()
                    .currencyName(currency.toString())
                    .baseCurrency(currency.toString().substring(0, 3))
                    .quoteCurrency(currency.toString().substring(3))
                    //.date(date)
                    .build();
            request.identifyBase();
            listofRequest.add(request);
        }

        Collections.sort(listofRequest);

        for(Request requestInto : listofRequest){
            result.append(requestInto);
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }

    public  Map<String,Object> parseResponse(){
        ratesMap = (Map<String, Double>) mapResp.get("rates");
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
        for(Response response: listOfResponse ){
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(response.getCurrencyName())
                    .price(response.getRightPrice())
                    .build();
            financialEntities.add(quotesLive);
        }
        mapResp.put("rates",financialEntities);
        return mapResp;
    }
}