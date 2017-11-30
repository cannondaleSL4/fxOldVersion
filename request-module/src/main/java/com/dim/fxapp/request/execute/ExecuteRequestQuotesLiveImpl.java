package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 30.11.17.
 */
public class ExecuteRequestQuotesLiveImpl extends ExecuteRequestAbstract {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpGet httpGet;

    @Override
    public QuotesLive getQuote(String currencyName) {
        return null;
    }

    @Override
    public Map<String,Object> getQuotes() {
        httpGet = new HttpGet(MAIN + LATEST + MYAPPID );

        try(CloseableHttpResponse response =  httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            Map<String, Object> mapResp = new ObjectMapper().readValue(EntityUtils.toString(entity), HashMap.class);

            if (mapResp.containsKey("error")){
                return mapResp;
            }

            int main = 25;
//            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
//            String formattedDate = dateFormat.format(timeStampDate);


            //System.out.println("1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")");
            //response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String,Object> getQuotes(List currenciesNames) {
        return null;
    }

}