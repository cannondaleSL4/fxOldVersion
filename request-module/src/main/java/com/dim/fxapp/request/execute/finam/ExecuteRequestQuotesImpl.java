package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.Request;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by dima on 29.11.17
 */

@Service("Quotes")
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes> {

    private final  String main;
    private final String mainForRequest;

    private Map<String, Object> mapResp;
    private Map<String,Double> ratesMap;

    private Map<Currency,Map<String,String>> mapHelper;

    public ExecuteRequestQuotesImpl(@Value("${currency.mainfinam}")String main, @Value("${currency.mainfinamrequest}") String mainForRequest ){
        super();
        this.main = main;
        this.mainForRequest = mainForRequest;
        mapResp = new HashMap<>(); // full response from server
        ratesMap = new HashMap<>(); // full response from server
    }

    @PostConstruct
    private void init(){
        mapHelper = new HashMap<>();
        for(String K :currencyList){
            if (StringUtils.isNotBlank(mainForRequest)){
                StringBuilder builder = new StringBuilder(K.toLowerCase());
                String html =mainForRequest + builder.insert(3,'-').toString();
                try {
                    Document doc = Jsoup.connect(html).get();
                    Element link = doc.getElementById("content-block").getElementsByTag("script").get(0);
                    String dataFromHTML = link.childNode(0).attr("data");

                    dataFromHTML = StringUtils.substringAfter(dataFromHTML,"Finam.IssuerProfile.Main.issue = ");
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Map<String, Object> map = mapper.readValue(dataFromHTML, new TypeReference<HashMap<String,Object>>(){});

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyDto {

        private String stringValue;
        private int intValue;
        private boolean booleanValue;

        public MyDto() {
            super();
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
