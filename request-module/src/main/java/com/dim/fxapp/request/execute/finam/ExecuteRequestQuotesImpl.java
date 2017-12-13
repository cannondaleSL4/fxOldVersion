package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dima on 29.11.17
 */
//@Component
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    @Value("${currency.mainfinam}")
    String MAIN;
    @Value("${currency.mainfinamrequest}")
    String MAIN_FOR_REQUEST;

    private Map<String, Object> mapResp;
    private Map<String,Double> ratesMap;

    private Map<Currency,Map<String,String>> mapHelper;

    public ExecuteRequestQuotesImpl(){
        super();
        this.MAIN = MAIN;
        this.MAIN_FOR_REQUEST = MAIN_FOR_REQUEST;
        mapResp = new HashMap<>(); // full response from server
        ratesMap = new HashMap<>(); // full response from server
        init();
    }

    @PostConstruct
    private void init(){
        mapHelper = new HashMap<>();
        for(String K :currencyList){
            if (StringUtils.isNotBlank(MAIN_FOR_REQUEST)){
                StringBuilder builder = new StringBuilder(K.toLowerCase());
                String html =MAIN_FOR_REQUEST + builder.insert(3,'-').toString();

                try {
                    Document doc = Jsoup.connect(html).get();
                    Element link = doc.getElementById("content-block").getElementsByTag("script").get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
