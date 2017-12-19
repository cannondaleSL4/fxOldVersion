package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.Criteria;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.CreateCtiteria;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {
    @Autowired
    @Qualifier("Quotes")
    private ExecuteRequestAbstract<Quotes, Criteria> getQuotes;

    @Autowired
    @Qualifier("LiveQuotes")
    private ExecuteRequestAbstract<QuotesLive,Criteria> getLiveQuotes;

    @Autowired
    private CreateCtiteria createCtiteria;

    /*@Autowired
    @Qualifier("today")
    protected Criteria<Criteria> today;

    @Autowired
    @Qualifier("date")
    protected Criteria<Criteria> date;

    @Autowired
    @Qualifier("dateFromTo")
    protected Criteria<Criteria> dateFromTo;*/

    @RequestMapping(value = "/livequotes" , method = RequestMethod.GET)
    public Map<String,Object> getQuotesLive() throws ServerRequestExeption {
        return getLiveQuotes.getQuotes(createCtiteria.getToday());
    }

    @RequestMapping(value ="/livequotes/{date}", method = RequestMethod.GET)
    public Map<String,Object> getQuotesLiveDate(@PathVariable("date")String strDate) throws ServerRequestDateExeption, ServerRequestExeption {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDateTime localDate = LocalDateTime.from(LocalDate.parse(strDate,formatter).atStartOfDay());

        return  getLiveQuotes.getQuotes(createCtiteria.getDate(localDate));
    }

    @RequestMapping(value = "quotes/{from}/{to}")
    public Map<String,Object> getQuotes(@PathVariable("from")String from,
                                        @PathVariable("to")String to) throws ServerRequestExeption, ServerRequestDateExeption {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDateTime dateFrom = LocalDateTime.from(LocalDate.parse(from,formatter).atStartOfDay());
        LocalDateTime dateTo = LocalDateTime.from(LocalDate.parse(from,formatter).atStartOfDay());

        return getQuotes.getQuotes(createCtiteria.getPeriod(dateFrom,dateTo));
    }



    @RequestMapping(value = "quotes/{from}")
    public Map<String,Object> getQuotes(@PathVariable("from")String from) throws ServerRequestExeption, ServerRequestDateExeption {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDateTime dateFrom = LocalDateTime.from(LocalDate.parse(from,formatter).atStartOfDay());
        LocalDateTime dateTo = LocalDateTime.now();

        return getQuotes.getQuotes(createCtiteria.getPeriod(dateFrom,dateTo));
    }

}