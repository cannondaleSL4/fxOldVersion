package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.Currency;
import com.dim.fxapp.request.execute.RequestFromServer;
import com.dim.fxapp.request.execute.RequestQuotesExecute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/test")
    public String home() {
        RequestQuotesExecute requestQuotesExecute = new RequestQuotesExecute(Arrays.asList(Currency.EURUSD,Currency.GBPUSD));

        return requestQuotesExecute.getLiveQuotes().toString();
    }
}