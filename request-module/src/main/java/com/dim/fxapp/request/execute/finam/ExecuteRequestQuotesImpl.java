package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Created by dima on 29.11.17
 */
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Override
    public Map<String, Object> getQuotes(LocalDateTime... dateArray) {
        List<String>request = new ArrayList<>();
        if (dateArray.length != 2){
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("error","incorrect date settings please check request format") ;
            return response;
        }
        request = getStringRequest(dateArray[0],dateArray[1]);
        return null;
    }

    @Override
    public List<String> getStringRequest(LocalDateTime... dateArray) {
        List<String> resultList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder result = new StringBuilder();
        result.append(MAIN + "="+ dateArray[0].format(DateTimeFormatter.ISO_DATE_TIME) + "&" +  "=" + dateArray[1].format(DateTimeFormatter.ISO_DATE_TIME) +
                   "=");
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
        return resultList;
    }

    @Override
    public Quotes getQuote(String currencyName) {
        return null;
    }


    @Override
    public Map<String,Object> getQuotes() {
        return null;
    }

}
