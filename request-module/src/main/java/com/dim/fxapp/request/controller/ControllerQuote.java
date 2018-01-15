package com.dim.fxapp.request.controller;

import com.dim.fxapp.request.abstractCL.ExecuteRequestAbstract;
import com.dim.fxapp.request.execute.exeption.ServerRequestDateExeption;
import com.dim.fxapp.request.execute.exeption.ServerRequestExeption;
import com.dim.fxapp.request.service.CreateCtiteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by dima on 26.12.17.
 */
@RestController
@RequestMapping("/quotes")
public class ControllerQuote {
    
    @Autowired
    @Qualifier("Quotes")
    private ExecuteRequestAbstract getQuotes;

    @Autowired
    private CreateCtiteria createCtiteria;

    @RequestMapping(value = "/{from}/{to}")
    public Map<String,Object> getQuotes(@PathVariable("from")String from,
                                        @PathVariable("to")String to) throws ServerRequestExeption, ServerRequestDateExeption {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDateTime dateFrom = LocalDateTime.from(LocalDate.parse(from,formatter).atStartOfDay());
        LocalDateTime dateTo = LocalDateTime.from(LocalDate.parse(to,formatter).atStartOfDay());
        return getQuotes.getQuotes(createCtiteria.getPeriod(dateFrom,dateTo));
    }
}
