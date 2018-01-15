package com.dim.fxapp.request.execute.finam;

import com.dim.fxapp.entity.enums.Period;
import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.abstractCL.Criteria;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.exeption.ServerRequestExeption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dima on 29.11.17
 */

@Service("Quotes")
public class ExecuteRequestQuotesImpl extends ExecuteRequestAbstract<Quotes,Criteria> {

    private final  String main;
    private final String mainForRequest;

    private Map<String, Object> mapResp;
    private Map<String,Double> ratesMap;
    private List<String>listOfPeriod;

    private Map<String,Integer> mapHelper;

    public ExecuteRequestQuotesImpl(@Value("${currency.mainfinam}")String main, @Value("${currency.mainfinamrequest}") String mainForRequest ){
        super();
        this.main = main;
        this.mainForRequest = mainForRequest;
        this.listOfPeriod = Arrays.asList(Period.values()).stream().map(K -> K.toString()).collect(Collectors.toList());
        mapResp = new HashMap<>(); // full response from server
        ratesMap = new HashMap<>(); // full response from server
    }

    /*@PostConstruct
    private void init() throws FinamError {
        mapHelper = new HashMap<>();
        Integer id;
        Pattern pattern = Pattern.compile("\"quote\":\\s(\\d{1,}),\\s");
        for(String K :currencyList){
            if (StringUtils.isNotBlank(mainForRequest)){
                StringBuilder builder = new StringBuilder(K.toLowerCase());
                String html =mainForRequest + builder.insert(3,'-').toString();
                try {
                    Document doc = Jsoup.connect(html).get();
                    Element link = doc.getElementById("content-block").getElementsByTag("script").get(0);
                    String dataFromHTML = link.childNode(0).attr("data");
                    Matcher matcher = pattern.matcher(dataFromHTML);
                    if(matcher.find()){
                        id = Integer.parseInt(matcher.group(1));
                    } else {
                        throw  new FinamError(String.format("Finam Error have no id for %s currenct",K));
                    }
                    mapHelper.put(K,id);
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
        mapResp = getServerResponse(getStringRequest(from,to,listOfPeriod));
        return mapResp;
    }

    @Override
    public List<String> getStringRequest(Object...objects) {
        this.from = (LocalDateTime) objects[0];
        this.to = (LocalDateTime) objects[1];
        List<String>  requestedPeriods = (List<String>) objects[2];
        List<String> listOfStringRequest = new ArrayList<>();
        Map<String,StringBuilder> temporaryMap = new HashMap<>();

        currencyList.forEach(K -> listofRequest.add(
                Request.builder()
                        .currencyName(K.toString())
                        .baseCurrency( K.toString().substring(0,3))
                        .quoteCurrency( K.toString().substring(3))
                        .from(from)
                        .to(to)
                        .build()));

        listofRequest.forEach(K -> K.identifyBase());

        Collections.sort(listofRequest);

        String tempBase = listofRequest.get(0).getBase();
        temporaryMap.put(tempBase,new StringBuilder());

        return listOfStringRequest;
    }*/

    @Override
    public Quotes getQuote(Criteria criteria) {
        return null;
    }

    @Override
    public Map<String, Object> getQuotes(Criteria criteria) throws ServerRequestExeption {
        return null;
    }

    @Override
    protected List<String> getStringRequest(Criteria criteria) {
        return null;
    }

//    @Override
//    protected void addListToMap(Map<String, Object> mapsForParse) {
//
//    }

//    @Override
//    protected Map<String, Object> getServerResponse(List<String> strRequest) throws ServerRequestExeption {
//        return null;
//    }
}
