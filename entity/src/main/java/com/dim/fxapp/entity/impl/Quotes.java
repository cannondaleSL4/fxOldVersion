package com.dim.fxapp.entity.impl;

import com.dim.fxapp.entity.FinancialEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by dima on 28.11.17.
 */

@Entity
public class Quotes implements FinancialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private DateTime datetime;

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private DateTime date;

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

        public Builder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder date(DateTime date){
            this.date = date;
            return this;
        }

        public Quotes build(){
            return new Quotes(this);
        }
    }

    public Quotes(){

    }

    private Quotes(Builder quoteBuild) {
        this.id = quoteBuild.id;
        this.name = quoteBuild.name;
        this.datetime = quoteBuild.date;
        this.price = quoteBuild.price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime date) {
        this.datetime = date;
    }
}
