package com.dim.fxapp.request.execute;

import com.dim.fxapp.request.interfaces.RequestCurrency;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dima on 29.11.17.
 */
public class RequestQuotesExecute implements RequestCurrency {
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String currencies;

    public static class Builder{

        String currencys;

        public Builder(){
        }

        public Builder currencyList(List<String> listofCurrency){
            String prefix = "";
            StringBuilder tempcurrencies = new StringBuilder();
            for(String currensy: listofCurrency){
                tempcurrencies.append(prefix);
                prefix = ",";
                tempcurrencies.append(currensy);
            }
            currencys = tempcurrencies.toString();
            return this;
        }
    }



    @Override
    public void sendLiveRequest() {

    }
}
