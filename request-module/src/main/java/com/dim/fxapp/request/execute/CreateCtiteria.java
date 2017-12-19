package com.dim.fxapp.request.execute;

import com.dim.fxapp.request.abstractCL.Criteria;
import com.dim.fxapp.request.criteris.DateCriteria;
import com.dim.fxapp.request.criteris.DateFromToCriteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by dima on 19.12.17.
 */
@Service
public class CreateCtiteria {
    public Criteria getToday(){
        return new DateCriteria();
    }

    public Criteria getDate(LocalDateTime date){
        return new DateCriteria(date);
    }

    public Criteria getPeriod(LocalDateTime from, LocalDateTime to){
        return  new DateFromToCriteria(from,to);
    }
}
