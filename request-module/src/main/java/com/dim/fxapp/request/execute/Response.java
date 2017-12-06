package com.dim.fxapp.request.execute;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


@Builder
@EqualsAndHashCode
public class Response {
    private String baseCurrency;
    private String quoteCurrency;
    private Double price;
    //private  static Set<String> setofBases;
    private String base = "";

    public BigDecimal getRightPrice(){
        if (this.price > 10) return new BigDecimal(this.price);

        return new BigDecimal(1);
    }
}
