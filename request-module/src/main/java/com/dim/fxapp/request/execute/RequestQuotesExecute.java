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
import java.util.List;

/**
 * Created by dima on 29.11.17.
 */
public class RequestQuotesExecute {
    private String ACCESS_KEY = "96748e760f44176b0ff16b234e204ea1";
    private String BASE_URL = "http://apilayer.net/api/";
    private String ENDPOINT = "live";
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String currencies;
    private JSONObject exchangeRates;

    public  RequestQuotesExecute(List<String> listOfCurrencies){
        String prefix = "";
        StringBuilder tempcurrencies = new StringBuilder();
        for(String currensy: listOfCurrencies){
            tempcurrencies.append(prefix);
            prefix = ",";
            tempcurrencies.append(currensy);
        }
        currencies = tempcurrencies.toString();
    }

    public String getLiveQuotes() {
        if (currencies == null){
            System.out.println("you have no any currency set up");
            return null;
        }

        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" + currencies);

        Quotes quotes = new Quotes();

        try(CloseableHttpResponse response =  httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
            return exchangeRates.toString();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exchangeRates.toString();
    }
}
