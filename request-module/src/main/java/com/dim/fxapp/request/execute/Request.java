package com.dim.fxapp.request.execute;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dima on 02.12.17.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Request implements Comparable<Request> {
    private String currencyName;
    private String requestedName;
    private String baseCurrency;
    private String quoteCurrency;
    @Builder.Default
    private String base = "USD" ;
    private LocalDateTime date;
    private LocalDateTime from;
    private LocalDateTime to;
    private static Set<String> setofBases = new HashSet<>();
    /*
    по размеру Set<String> setofBases можно будет узнавать сколько запросов надо будет слать если можно будет изменять base
    т.е. AUDUSD и AUDJPY это двумя разными запросами, тк. AUDUSD у них храниться как фактически USDAUD
     */



    public void identifyBase(){
        if (quoteCurrency.equals("USD")){
            this.requestedName = baseCurrency;
        } else if (baseCurrency.equals("USD")) {
            this.baseCurrency = "USD";
            this.requestedName = quoteCurrency;
        } else {
            this.base = this.baseCurrency;
            this.requestedName = quoteCurrency;
        }
        setofBases.add(base);
    }

    @Override
    public int compareTo(Request another) {

        if (this.base.equals(another.base))return 0 ;

        if (another.base.equals("USD") &&
                !this.base.equals("USD")) return 1; //first we will take USD

        if (!another.base.equals("USD") &&
                this.base.equals("USD")) return -1; //first we will take USD

        return this.base.compareTo(another.base); // sorting currency like string
    }

    @Override
    public String toString() {
        if (base.equals("USD")) return (baseCurrency.equals("USD")) ? quoteCurrency+"," : baseCurrency+",";
        return quoteCurrency + ",";
    }
}
