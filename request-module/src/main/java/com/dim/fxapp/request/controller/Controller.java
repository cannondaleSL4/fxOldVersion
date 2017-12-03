package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {

    @Autowired
    @Qualifier("LiveQuotes")
    private ExecuteRequestAbstract<QuotesLive> getLiveQuotes;


    @Autowired
    @Qualifier("Quotes")
    private ExecuteRequestAbstract<Quotes> getQuotes;

    @RequestMapping(value = "/livequotes")
    public List<QuotesLive> getQuotesLive() {
        return getLiveQuotes.getQuotes();
    }
}