package com.dim.fxapp.entity.impl;

import com.dim.fxapp.entity.FinancialEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by dima on 28.11.17.
 */

@Entity
public class QuotesLive implements FinancialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate localDate;

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private LocalDate date;

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

        public Builder date(LocalDate date){
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
        this.localDate = quoteBuild.date;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate date) {
        this.localDate = date;
    }
}
