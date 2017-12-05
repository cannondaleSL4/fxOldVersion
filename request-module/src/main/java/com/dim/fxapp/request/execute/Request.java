package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.exeption.CurrencyRequestExeption;

import java.util.Date;

/**
 * Created by dima on 02.12.17.
 */
/*
i have no any idea what this class for )))

 */
public class Request {
    private String baseCurrency;
    private String quoteCurrency;
    private Date date;
    private Date from;
    private Date to;

    public static class Builder {
        private String baseCurrency;
        private String quoteCurrency;
        private Date date;
        private Date from;
        private Date to;

        public Builder(){

        }

        public Builder name(Currency currency) throws CurrencyRequestExeption {
            if (currency.toString().length() != 6) throw new CurrencyRequestExeption("Currency name is incorrect!");
            this.baseCurrency = currency.toString().substring(0,3);
            this.quoteCurrency = currency.toString().substring(3);
            return this;
        }

        public Builder date(Date date){
            this.date = date;
            return this;
        }

        public Builder from(Date from){
            this.from = from;
            return this;
        }

        public Builder to(Date to){
            this.to = to;
            return this;
        }

        public Request build(){
            return new Request(this);
        }
    }

    public Request(){

    }

    public Request (Builder builder){
        this.baseCurrency = builder.baseCurrency;
        this.quoteCurrency = builder.quoteCurrency;
        this.date = builder.date;
        this.from = builder.from;
        this.to = builder.to;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public Date getDate() throws CurrencyRequestExeption {
        if(this.date == null)throw new CurrencyRequestExeption("date is not specified");
        return date;
    }

    public Date getFrom() throws CurrencyRequestExeption {
        if(this.from == null)throw new CurrencyRequestExeption("date \"from\" is not specified");
        return from;
    }

    public Date getTo() throws CurrencyRequestExeption {
        if(this.to == null)throw new CurrencyRequestExeption("date \"to\" is not specified");
        return to;
    }
}
