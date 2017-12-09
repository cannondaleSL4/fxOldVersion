package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dima on 29.11.17.
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
        if (dateArray.length != 1){
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("error","incorrect date settings please check request format") ;
            return response;
        }

        String request = getStringRequest(dateArray[0],dateArray[1]);
        return null;
    }

    @Override
    public String getStringRequest(LocalDateTime... dateArray) {
        StringBuilder result = new StringBuilder();
        result.append(MAIN + OHLC + MYAPPID + "&" + START + "="+ dateArray[0] + "&" + END +
                  SYMBOLS + "=");
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
    public Quotes getQuote(String currencyName) {
        return null;
    }


    @Override
    public Map<String,Object> getQuotes() {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes(List currenciesNames) {
        return null;
    }
}
