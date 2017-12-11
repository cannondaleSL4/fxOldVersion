package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.execute.Response;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive> {
    private Map<String, Object> mapResp; // full response from server
    private Map<String,Double> ratesMap; // only rates map from mapResp

    @Override
    public QuotesLive getQuote(String currencyName) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes() {
        mapResp = getServerResponse(getStringRequest());
        return mapResp.containsKey("error") ? mapResp : parseResponse();
    }

    @Override
    public List<String> getStringRequest(){
        List<String> listOfStringRequest = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        Map<String,StringBuilder> temporaryMap = new HashMap<String,StringBuilder>();
        listofRequest = new ArrayList<>();

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


        for(Request request: listofRequest){
            if(request.getBase().equals(tempBase)){
                temporaryMap.put(tempBase,temporaryMap.get(tempBase).append(request.getRequestedName() + ","));
            }else{
                tempBase = request.getBase();
                temporaryMap.put(tempBase,new StringBuilder().append(request.getRequestedName() + ","));
            }
        }

        temporaryMap.forEach((K,V)-> {
            listOfStringRequest.add(String.format(MAIN,K,V.deleteCharAt(V.length()-1)));
        });

        return listOfStringRequest;
    }

    @Override
    public Map<String,Object> getServerResponse(List<String> strRequest){
        Map<String,Object> responseMap= new HashMap<>();
        Map<String,Object> localMap;

        for(String str: strRequest){
            httpGet = new HttpGet(str);
            try(CloseableHttpResponse response =  httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                localMap = new ObjectMapper().readValue(EntityUtils.toString(entity), HashMap.class);
                if(localMap.containsKey("error")) throw new ServerRequestExeption((String)localMap.get("error"));
                addListToMap(localMap);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServerRequestExeption serverRequestExeption) {
                serverRequestExeption.printStackTrace();
            }
        }
        return responseMap;
    }

    @Override
    public void addListToMap(Map<String,Object> mapsForParse){
        Map<String,Object> rateMap = new HashMap<>();
        rateMap = (Map<String, Object>) mapsForParse.get("rates");
        String base = (String) mapsForParse.get("base");
        List<QuotesLive> financialEntities = new ArrayList<QuotesLive>();
        rateMap.forEach((V,K) ->{
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(V)
                    .price((Double) K)
                    .base(base)
                    //.date(response.getDate())
                    .build();
            financialEntities.add(quotesLive);
        });
    }

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
        if (dateArray.length != 1){
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("error","incorrect date settings please check request format") ;
            return response;
        }
        List<String>request = getStringRequest(dateArray[0]);

        mapResp = getServerResponse(request);

        return mapResp.containsKey("error") ? mapResp : parseResponse();
    }



    @Override
    public List<String> getStringRequest(LocalDateTime... date){
        StringBuilder result = new StringBuilder();
        //result.append(MAIN  + date[0].toLocalDate() + ".json?"  + "&" + SYMBOLS + "=");//format(DateTimeFormatter.ISO_DATE_TIME)
        listofRequest = new LinkedList<Request>();
        Request request;
        for(Currency currency : currencyList) {
            request = Request.builder()
                    .currencyName(currency.toString())
                    .baseCurrency(currency.toString().substring(0, 3))
                    .quoteCurrency(currency.toString().substring(3))
                    .date(date[0])
                    .build();
            request.identifyBase();
            listofRequest.add(request);
        }

        Collections.sort(listofRequest);

        for(Request requestInto : listofRequest){
            result.append(requestInto);
        }

        result.setLength(result.length() - 1);
        //return result.toString();
        return null;
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
        //TODO рассмотреть возможность убрать Response вообще убрать ))
        for(Response response: listOfResponse ){
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(response.getCurrencyName())
                    .price(response.getPrice())
                    .date(response.getDate())
                    .build();
            financialEntities.add(quotesLive);
        }
        mapResp.put("rates",financialEntities);
        return mapResp;
    }
}