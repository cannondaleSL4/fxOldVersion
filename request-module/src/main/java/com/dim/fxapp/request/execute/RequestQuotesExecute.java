package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.request.interfaces.RequestCurrency;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.dim.fxapp.request.execute.RequestFromServer.ENDPOINT;

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
    public LinkedList<Quotes> getLiveQuotes() {
        if (currencies == null){
            System.out.println("you have no any currency set up");
            return null;
        }

        HttpGet get = new HttpGet(BASE_URL + ENDPOINT_LIVE + "?access_key=" + ACCESS_KEY);

        Quotes quotes = new Quotes();

        try(CloseableHttpResponse response =  httpClient.execute(get)) {

            HttpEntity entity = response.getEntity();

            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = dateFormat.format(timeStampDate);

            //System.out.println("1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")");

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

        return null;

    }
}
