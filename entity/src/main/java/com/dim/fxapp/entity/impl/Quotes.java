package com.dim.fxapp.entity.impl;

import com.dim.fxapp.entity.FinancialEntity;
import com.dim.fxapp.entity.enums.Period;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by dima on 28.11.17.
 */
@Entity
public class Quotes extends FinancialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="period", nullable = false)
    @Enumerated(EnumType.STRING)
    private Period period;

    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime date;

    @Column(name="open", nullable = false)
    private BigDecimal open;

    @Column(name="close", nullable = false)
    private BigDecimal close;

    @Column(name="hight", nullable = false)
    private BigDecimal hight;

    @Column(name="low", nullable = false)
    private BigDecimal low;

    public Quotes() {
    }

    public static class Builder {
        private Long id;
        private String name;
        private Period period;
        private BigDecimal open;
        private BigDecimal close;
        private BigDecimal hight;
        private BigDecimal low;
        private LocalDateTime date;

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

        public Builder date(LocalDateTime date){
            this.date = this.date;
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

        public Quotes build(){
            return new Quotes(this);
        }
    }

    private Quotes(Builder stockBuild){
        name = stockBuild.name;
        period = stockBuild.period;
        date =stockBuild.date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public Period getPeriod() {
        return period;
    }
}
