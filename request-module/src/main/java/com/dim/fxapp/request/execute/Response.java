package com.dim.fxapp.request.execute;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Builder
@EqualsAndHashCode
public class Response {
    @NonNull
    private String baseCurrency;
    @NonNull
    private String quoteCurrency;
    @NonNull
    private Double price;
    @NonNull
    private String base;
    //private  static Set<String> setofBases;

    public BigDecimal getRightPrice(){
        BigDecimal temp = new BigDecimal(price);

        if (this.price > 10) return new BigDecimal(this.price).setScale(4, RoundingMode.HALF_UP);

        return BigDecimal.ONE.divide(temp,4,RoundingMode.HALF_UP);
    }
}