package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.Quotes;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dima on 29.11.17.
 */
public class RequestQuotesExecute {
    private String ACCESS_KEY = "96748e760f44176b0ff16b234e204ea1";
    private String BASE_URL = "http://apilayer.net/api/";
    private String ENDPOINT = "live";
    private JSONObject exchangeRates;
    private String currencies="";
    private List<String> listOfCurrencies = new ArrayList<String>();


    public  RequestQuotesExecute(List<String> listOfCurrencies){

        this.listOfCurrencies = listOfCurrencies;

        String prefix = "";
        StringBuilder tempcurrencies = new StringBuilder();
        for(String currensy: listOfCurrencies){
            tempcurrencies.append(prefix);
            prefix = ",";
            tempcurrencies.append(currensy);
        }
        currencies = tempcurrencies.toString();
    }

    public Map<String,Object> getLiveQuotes() {
        CloseableHttpClient httpClient = HttpClients.createDefault();


        if (currencies == null){
            System.out.println("you have no any currency set up");
            return null;
        }

        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" + currencies);

        Quotes quotes = new Quotes();

        try(CloseableHttpResponse response =  httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            exchangeRates = new JSONObject(EntityUtils.toString(entity));
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseResponce(exchangeRates);
    }

    private Map<String,Object> parseResponce(JSONObject response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean success;
        Date date;
        List<Quotes> listOfResponse = new ArrayList<Quotes>();

        try {
            success = exchangeRates.getBoolean("success");
            if (success){
                date = new Date();
                for(String str: listOfCurrencies){
                    Quotes tempQuote = new Quotes.Builder()
                            .name(getName(str))
                            .price(getPrice(str))
                            .build();
                    listOfResponse.add(tempQuote);
                }
                map.put("success",success);
                map.put("data",date);
                map.put("quotes",listOfResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    private String getName(String anotherName){
        if (anotherName.equals("JPY"))return anotherName + "USD";
        return "USD" + anotherName;
    }

    private BigDecimal getPrice(String anotherName) throws JSONException {
        if (anotherName.equals("JPY")) return new BigDecimal(exchangeRates.getJSONObject("quotes").getDouble("USD"+anotherName));
        return new BigDecimal(1 / exchangeRates.getJSONObject("quotes").getDouble("USD" + anotherName));
    }
}
