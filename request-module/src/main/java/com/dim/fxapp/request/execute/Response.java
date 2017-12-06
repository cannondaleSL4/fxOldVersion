package com.dim.fxapp.request.execute;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by dima on 06.12.17.
 */

/*
but i can use @Data
https://projectlombok.org/features/Data
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Response {
    private String baseCurrency;
    private String quoteCurrency;
    private Double price;
    private  static Set<String> setofBases;
    private String base = "";

    public BigDecimal getRightPrice(){
        if (this.price > 10) return new BigDecimal(this.price);

        BigDecimal
    }
}
