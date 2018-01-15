package com.dim.fxapp.request.criteris;

import com.dim.fxapp.request.abstractCL.Criteria;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by dima on 16.12.17.
 */
@Getter
public class DateCriteria extends Criteria {

    public DateCriteria(LocalDateTime date){
        super();
        this.date = date;
    }

    public DateCriteria(String requestedCurrency, LocalDateTime localDateTime){
        super(requestedCurrency);
        this.date = localDateTime;
    }

    public DateCriteria(){
        super();
        this.date = LocalDateTime.now();
    }

    public DateCriteria(String requestedCurrency){
        super(requestedCurrency);
        this.date = LocalDateTime.now();
    }
}
