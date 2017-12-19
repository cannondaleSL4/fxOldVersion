package com.dim.fxapp.request.abstractCL;

import com.dim.fxapp.entity.enums.Currency;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dima on 16.12.17.
 */

@Getter
public abstract class Criteria <C extends Criteria> {

    protected Class<C> type;

    protected List<String> listOfCurrency;
    protected LocalDateTime date;
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Criteria(){
        listOfCurrency = Arrays.asList(Currency.values())
                .stream()
                .map(K -> K.toString())
                .collect(Collectors.toList());
    }

    public Criteria(String requestedCurrency){
        listOfCurrency = Arrays.asList(Currency.values())
                .stream()
                .filter(K -> K.toString().equals(requestedCurrency))
                .map(K -> K.toString())
                .collect(Collectors.toList());
    }

    public Criteria(Class<C> type){
        this.type = type;
    }

    public C copy() throws Exception {
        return type.newInstance();
    }

}
