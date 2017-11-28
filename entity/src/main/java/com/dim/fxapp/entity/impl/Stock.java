package com.dim.fxapp.entity.impl;

import com.dim.fxapp.entity.FinancialEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by dima on 28.11.17.
 */
@Entity
public class Stock implements FinancialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="period", nullable = false)
    @Enumerated(EnumType.STRING)
    private Period period;

    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private DateTime datetime;

    @Column(name="open", nullable = false)
    private BigDecimal open;

    @Column(name="close", nullable = false)
    private BigDecimal close;

    @Column(name="hight", nullable = false)
    private BigDecimal hight;

    @Column(name="low", nullable = false)
    private BigDecimal low;

    public Stock() {
    }

    public static class Builder {
        private Long id;
        private String name;
        private Period period;
        private BigDecimal open;
        private BigDecimal close;
        private BigDecimal hight;
        private BigDecimal low;
        private DateTime dateTime;

        public Builder(){ }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder period(Period period){
            this.period = period;
            return this;
        }

        public Builder date(DateTime date){
            this.dateTime = dateTime;
            return this;
        }

        public Builder open(BigDecimal open) {
            this.open = open;
            return this;
        }

        public Builder close(BigDecimal close) {
            this.close = close;
            return this;
        }

        public Builder hight(BigDecimal hight) {
            this.hight = hight;
            return this;
        }

        public Builder low(BigDecimal low) {
            this.low = low;
            return this;
        }

        public Stock build(){
            return new Stock(this);
        }
    }

    private Stock(Builder stockBuild){
        name = stockBuild.name;
        period = stockBuild.period;
        datetime  =stockBuild.dateTime;
        open = stockBuild.open;
        close = stockBuild.close;
        hight = stockBuild.hight;
        low = stockBuild.low;
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

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getHight() {
        return hight;
    }

    public BigDecimal getLow() {
        return low;
    }

    public DateTime getDate() {
        return datetime;
    }

    public Period getPeriod() {
        return period;
    }
}
