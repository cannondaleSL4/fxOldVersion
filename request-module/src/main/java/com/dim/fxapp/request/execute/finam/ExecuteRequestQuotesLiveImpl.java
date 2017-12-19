package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.Criteria;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
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
import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
@Service("LiveQuotes")
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive,Criteria> {

    @Value("${currency.main}")
    protected String MAIN;
    private final String latest = "latest";

    /*@Override
    public Map<String,Object> getQuotes() throws ServerRequestExeption {
        mapResp = getServerResponse(getStringRequest(new DateCriteria()));
        return mapResp;
    }*/

    @Override
    public Map<String, Object> getQuotes(Criteria criteria) throws ServerRequestExeption {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //criteria.getDate().format(formatter)
        mapResp = getServerResponse(getStringRequest(criteria));
        return mapResp;
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
    public List<String> getStringRequest(Criteria criteria){
        List<String> listOfStringRequest = new ArrayList<>();
        Map<String,StringBuilder> temporaryMap = new HashMap<>();

        List<String> tempList = criteria.getListOfCurrency();
        tempList.forEach(K -> listofRequest.add(
                Request.builder()
                    .currencyName(K)
                    .baseCurrency( K.substring(0,3))
                    .quoteCurrency( K.substring(3))
                    .build()));

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
            listOfStringRequest.add(String.format(MAIN,latest,K,V.deleteCharAt(V.length()-1)));
        });

        return listOfStringRequest;
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
                    .date(LocalDateTime.parse((String)mapsForParse.get("date")))
                    .build();
            financialEntities.add(quotesLive);
        });
        mapResp.remove("rates");
        mapResp.remove("base");
    }

    @Override
    public QuotesLive getQuote(Criteria criteria) {
        return null;
    }
}