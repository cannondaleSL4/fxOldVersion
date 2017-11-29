package com.dim.fxapp.request.interfaces;

import com.dim.fxapp.entity.Currency;
import com.dim.fxapp.entity.impl.Quotes;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

/**
 * Created by dima on 29.11.17.
 */
public interface RequestCurrency {
    String ACCESS_KEY = "96748e760f44176b0ff16b234e204ea1";
    String BASE_URL = "http://apilayer.net/api/";
    String ENDPOINT_LIVE = "live";
    String ENDPOINT_HIST = "historical";

    List<Quotes> getLiveQuotes();
}
