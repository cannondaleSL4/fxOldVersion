package com.dim.fxapp.request.criteris;

import com.dim.fxapp.request.abstractCL.Criteria;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by dima on 16.12.17.
 */
@Builder
@Getter
//@Service("dateFromToCriteria")
public class DateFromToCriteria extends Criteria {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public DateFromToCriteria(LocalDateTime from, LocalDateTime to){
        super();
        this.from = from;
        this.to = to;
    }

    public DateFromToCriteria(String requestedCurrency,LocalDateTime from, LocalDateTime to){
        super(requestedCurrency);
        this.from = from;
        this.to = to;
    }

}
