package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {

    @Autowired
    @Qualifier("LiveQuotes")
    private ExecuteRequest<QuotesLive> getLiveQuotes;


    @Autowired
    @Qualifier("Quotes")
    private ExecuteRequest<Quotes> getQuotes;

    @RequestMapping(value = "/livequotes")
    public Map<String,Object> getQuotesLive() {
        return null;
    }
}