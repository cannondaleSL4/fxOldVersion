package com.dim.fxapp.entity.impl;

import com.dim.fxapp.entity.FinancialEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Created by dima on 28.11.17.
 */

@Entity
public class QuotesLive extends FinancialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    private String base;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime localDateTime;

    public static class Builder {
        private Long id;
        private String name;
        private String base;
        private BigDecimal price;
        private LocalDateTime date;

        public Builder(){
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder price(Double price){
            if(!base.equals("USD")){
                this.price = (price > 10) ? (new BigDecimal(price).setScale(2, RoundingMode.HALF_UP)) : (new BigDecimal(price).setScale(4, RoundingMode.HALF_UP));
            } else {
                if (name.contains("JPY") || name.contains("CAD") ){
                    this.price = (price > 10) ? (new BigDecimal(price).setScale(2, RoundingMode.HALF_UP)) : (new BigDecimal(price).setScale(4, RoundingMode.HALF_UP));
                } else{
                    this.price = BigDecimal.ONE.divide(new BigDecimal(price),4,RoundingMode.HALF_UP);
                }
            }
            return this;
        }

        public Builder base(String base){
            this.base = base;
            if (!base.equals("USD")){
                this.name = base + this.name;
                return this;
            }

            if(this.name.equals("JPY") ||
                    this.name.equals("CAD")){
                this.name = base + this.name;
                return this;
            }

            this.name = name + base;
            return this;
        }

        public Builder date(LocalDateTime date){
            this.date = date;
            return this;
        }

        public QuotesLive build(){
            return new QuotesLive(this);
        }
    }

    public QuotesLive(){

    }

    private QuotesLive(Builder quoteBuild) {
        this.id = quoteBuild.id;
        this.name = quoteBuild.name;
        this.localDateTime = quoteBuild.date;
        this.price = quoteBuild.price;
        this.base = quoteBuild.base;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
