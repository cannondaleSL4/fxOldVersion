package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

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

    @RequestMapping(value = "/quotes" , method = RequestMethod.GET)
    public Map<String,Object> getQuotesLive() {
        return getLiveQuotes.getQuotes();
    }

    @RequestMapping(value ="/quotes/{date}", method = RequestMethod.GET)
    public Map<String,Object> getQuotesDate(@PathVariable("date")String date){
        int [] dateInt = Arrays.asList(date.split("-")).stream().mapToInt(Integer::parseInt).toArray();
        return getLiveQuotes.getQuotes(LocalDate.of(dateInt[0],dateInt[1],dateInt[2]));
    }

    @RequestMapping(value = "/history/{from}/{to}", method = RequestMethod.GET)
    public Map<String,Object> getHistory(@PathVariable("from") String from,
                                         @PathVariable("to") String to){

        int [] fromInt = Arrays.asList(from.split("-")).stream().mapToInt(Integer::parseInt).toArray();
        int [] toInt = Arrays.asList(from.split("-")).stream().mapToInt(Integer::parseInt).toArray();

        // int year, int month, int dayOfMonth

        LocalDate fromDate = LocalDate.of(fromInt[0],fromInt[1],fromInt[2]);
        LocalDate toDate = LocalDate.of(toInt[0],toInt[1],toInt[2]);

        return getQuotes.getQuotes(fromDate,toDate);
    }
}