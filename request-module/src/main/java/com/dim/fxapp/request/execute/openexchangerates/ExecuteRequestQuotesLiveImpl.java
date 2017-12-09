package com.dim.fxapp.request.execute.openexchangerates;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.execute.Response;

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
        String request = getStringRequest();
        mapResp = getServerResponse(request);
        return mapResp.containsKey("error") ? mapResp : parseResponse();
    }

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
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

    @Override
    public String getStringRequest(LocalDateTime... date){
        StringBuilder result = new StringBuilder();
        result.append(MAIN + HISTORICAL + date[0].toLocalDate() + ".json?" + MYAPPID + "&" + SYMBOLS + "=");//format(DateTimeFormatter.ISO_DATE_TIME)
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
        //TODO рассмотреть возможность убрать Response вообще убрать ))
        for(Response response: listOfResponse ){
            QuotesLive quotesLive = new QuotesLive.Builder()
                    .name(response.getCurrencyName())
                    .price(response.getRightPrice())
                    .date(response.getDate())
                    .build();
            financialEntities.add(quotesLive);
        }
        mapResp.put("rates",financialEntities);
        return mapResp;
    }
}