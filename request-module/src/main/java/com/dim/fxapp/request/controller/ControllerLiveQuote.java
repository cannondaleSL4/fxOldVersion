package com.dim.fxapp.request.controller;

import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.execute.exeption.ServerRequestExeption;
import com.dim.fxapp.request.service.CreateCtiteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by dima on 18.11.17.
 */
@RestController
@RequestMapping("/livequotes")
public class ControllerLiveQuote {

    @Autowired
    @Qualifier("LiveQuotes")
    private ExecuteRequestAbstract getLiveQuotes;

    @Autowired
    private CreateCtiteria createCtiteria;

    String now = LocalDateTime.now().toString();

    @RequestMapping(value = "/")
    public Map<String,Object> getQuotesLive(@RequestParam(value = "date",
            defaultValue= "")String date) throws ServerRequestExeption {
        return getLiveQuotes.getQuotes(createCtiteria.getToday());
    }

    @RequestMapping(value ="/{date}", method = RequestMethod.GET)
    public Map<String,Object> getQuotesLiveDate(@PathVariable("date")String strDate) throws ServerRequestDateExeption, ServerRequestExeption {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDateTime localDate = LocalDateTime.from(LocalDate.parse(strDate,formatter).atStartOfDay());

        return  getLiveQuotes.getQuotes(createCtiteria.getDate(localDate));
    }

}