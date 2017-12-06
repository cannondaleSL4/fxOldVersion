package com.dim.fxapp.request.execute;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Date;
import java.util.Set;

/**
 * Created by dima on 02.12.17.
 */
@Builder
@EqualsAndHashCode
public class Request implements Comparable<Request> {
    @NonNull
    private String currensyName;
    @NonNull
    private String baseCurrency;
    @NonNull
    private String quoteCurrency;
    @NonNull
    private String base;
    private Date date;
    private Date from;
    private Date to;
    private  static Set<String> setofBases;

    public void identifyBase(){
        if (quoteCurrency.equals("USD")){
            this.base = "USD";
        } else if (baseCurrency.equals("USD")) {
            this.baseCurrency = "USD";
        } else {
            base = baseCurrency;
        }
        setofBases.add(base);
    }

    @Override
    public int compareTo(Request another) {
        if (this.base.equals(another.base))return 0 ;

        if (this.base.equals("USD")) return 10; //first we will take USD

        return this.base.compareTo(another.base); // sorting currency like string
    }

    @Override
    public String toString() {
        if (base.equals("USD")) return (baseCurrency.equals("USD")) ? quoteCurrency+"," : baseCurrency+",";
        return quoteCurrency + ",";
    }
}
