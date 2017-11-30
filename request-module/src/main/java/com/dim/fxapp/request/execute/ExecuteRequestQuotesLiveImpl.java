package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive> {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpGet httpGet;
    private Map<String, Object> mapResp;

    @Override
    public QuotesLive getQuote(String currencyName) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes() {
        String request = getStringRequest();
        httpGet = new HttpGet(request );
        try(CloseableHttpResponse response =  httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            mapResp = new ObjectMapper().readValue(EntityUtils.toString(entity), HashMap.class);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapResp.containsKey("error") ? mapResp : parseResponse(mapResp);
    }

    @Override
    public Map<String, Object> getQuotes(List<String> currenciesNames) {
        return null;
    }

    private Map<String,Object> parseResponse(Map<String,Object> response){
        List<QuotesLive> liveList = new LinkedList<QuotesLive>();

        Map<String,Double> tempMap = (Map<String, Double>) response.get("rates");

        return new HashMap<String,Object>();
    }

    private String getStringRequest(){
        String str = MAIN + LATEST + MYAPPID;

        for(Currency currency : currencyList){

        }

        return str;
    }

}