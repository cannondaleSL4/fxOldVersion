package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.Criteria;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.execute.exeption.ServerRequestExeption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dima on 30.11.17.
 */
@Service("LiveQuotes")
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract<QuotesLive,Criteria> {

    @Value("${currency.main}")
    protected String MAIN;
    private final String latest = "latest";

    @Override
    public Map<String, Object> getQuotes(Criteria criteria) throws ServerRequestExeption {
        mapResp = getServerResponse(getStringRequest(criteria));
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
            listOfStringRequest.add(String.format(MAIN,latest,K,V.deleteCharAt(V.length()-1)));
        });

        return listOfStringRequest;
    }

    @Override
    public QuotesLive getQuote(Criteria criteria) {
        return null;
    }
}