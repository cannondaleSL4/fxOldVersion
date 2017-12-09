package com.dim.fxapp.request.execute.openexchangerates;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
// https://docs.openexchangerates.org/
/**
 * Created by dima on 29.11.17.
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
        if (dateArray.length != 2){
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("error","incorrect date settings please check request format") ;
            return response;
        }

        String request = getStringRequest(dateArray[0],dateArray[1]);
        return null;
    }

    @Override
    public String getStringRequest(LocalDateTime... dateArray) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder result = new StringBuilder();
        result.append(MAIN + OHLC + MYAPPID + "&" + START + "="+ dateArray[0].format(DateTimeFormatter.ISO_DATE_TIME) + "&" + END + "=" + dateArray[1].format(DateTimeFormatter.ISO_DATE_TIME) +
                  SYMBOLS + "=");
        listofRequest = new LinkedList<Request>();
        Request request;
        for(Currency currency : currencyList){
            request = Request.builder()
                    .currencyName(currency.toString())
                    .baseCurrency( currency.toString().substring(0,3))
                    .quoteCurrency( currency.toString().substring(3))
                    .from(dateArray[0])
                    .to(dateArray[1])
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
