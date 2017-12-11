package com.dim.fxapp.request.controller;

import com.dim.fxapp.entity.impl.Quotes;
import com.dim.fxapp.entity.impl.QuotesLive;
import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.exeption.ServerRequestExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {
    private List<Integer> dateFirst;
    private List<Integer> dateSecond;

    @Autowired
    @Qualifier("LiveQuotes")
    private ExecuteRequestAbstract<QuotesLive> getLiveQuotes;

    @Autowired
    @Qualifier("Quotes")
    private ExecuteRequestAbstract<Quotes> getQuotes;

    @RequestMapping(value = "/quotes" , method = RequestMethod.GET)
    public Map<String,Object> getQuotesLive() throws ServerRequestExeption {
        return getLiveQuotes.getQuotes();
    }

    @RequestMapping(value ="/quotes/{date}", method = RequestMethod.GET)
    public Map<String,Object> getQuotesDate(@PathVariable("date")String date) throws ServerRequestDateExeption, ServerRequestExeption {

        dateFirst = getArrayList(date);

        return getLiveQuotes.getQuotes(LocalDateTime.of(dateFirst.get(0), dateFirst.get(1), dateFirst.get(2), dateFirst.get(3), dateFirst.get(4)));
    }

    @RequestMapping(value = "/history/{from}/{to}", method = RequestMethod.GET)
    public Map<String,Object> getHistory(@PathVariable("from") String from,
                                         @PathVariable("to") String to) throws ServerRequestDateExeption, ServerRequestExeption {
        dateFirst = getArrayList(from);
        dateSecond = getArrayList(to);

        // int year, int month, int dayOfMonth int hours int minutes
        LocalDateTime fromDate = LocalDateTime.of(dateFirst.get(0),dateFirst.get(1),dateFirst.get(2),dateFirst.get(3),dateFirst.get(4));
        LocalDateTime toDate = LocalDateTime.of(dateSecond.get(0),dateSecond.get(1),dateSecond.get(2),dateSecond.get(4),dateSecond.get(5));

        return getQuotes.getQuotes(fromDate,toDate);
    }

    //если не указанно ДО - то считать это ДО Сейчас.
    @RequestMapping(value = "/history/{from}", method = RequestMethod.GET)
    public Map<String,Object> getHistory(@PathVariable("from") String from) throws ServerRequestDateExeption, ServerRequestExeption {
        dateFirst = getArrayList(from);

        // int year, int month, int dayOfMonth int hours int minutes
        LocalDateTime fromDate = LocalDateTime.of(dateFirst.get(0),dateFirst.get(1),dateFirst.get(2),dateFirst.get(3),dateFirst.get(4));
        LocalDateTime toDate = LocalDateTime.now();

        return getQuotes.getQuotes(fromDate,toDate);
    }

    // надо проставить значения для часов и минут, если была обозначенна только дата
    private List<Integer> getArrayList(String date){
        List<Integer> list = Arrays.asList(date.split("-"))
                .stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        if(list.size() == 3){
            list.add(3,0);
            list.add(4,0);
        }
        return list;
    }
}