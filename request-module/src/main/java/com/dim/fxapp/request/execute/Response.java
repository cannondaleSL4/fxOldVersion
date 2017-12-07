package com.dim.fxapp.request.execute;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Builder
@EqualsAndHashCode
public class Response {
    @Getter
    private String currencyName;
    private String baseCurrency;
    private String quoteCurrency;
    private Double price;
    private String base;
    //private  static Set<String> setofBases;

    public BigDecimal getRightPrice(){
        BigDecimal temp = new BigDecimal(price);

        if (this.price > 10) return new BigDecimal(this.price).setScale(2, RoundingMode.HALF_UP);

        return BigDecimal.ONE.divide(temp,4,RoundingMode.HALF_UP);
    }
}