package com.dim.fxapp.request.execute;

import com.dim.fxapp.entity.enums.Currency;
import com.dim.fxapp.request.exeption.CurrencyRequestExeption;

import java.util.*;

/**
 * Created by dima on 02.12.17.
 */

public class Request implements Comparable<Request> {
    private String baseCurrency;
    private String quoteCurrency;
    private Date date;
    private Date from;
    private Date to;
    private  static Set<String> setofBases;
    private String base = "";

    public static class Builder {
        private String baseCurrency;
        private String quoteCurrency;
        private Date date;
        private Date from;
        private Date to;
        private String base;
        private static Set<String> setofBases = new HashSet<String>();

        public Builder(){

        }

        public Builder name(Currency currency) throws CurrencyRequestExeption {
            if (currency.toString().length() != 6) throw new CurrencyRequestExeption("Currency name is incorrect!");
            this.baseCurrency = currency.toString().substring(0,3);
            this.quoteCurrency = currency.toString().substring(3);

            if (quoteCurrency.equals("USD")){
                this.base = "USD";
            } else if (baseCurrency.equals("USD")) {
                this.baseCurrency = "USD";
            } else {
                base = baseCurrency;
            }
            setofBases.add(base);

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
        this.base = builder.base;
        this.date = builder.date;
        this.from = builder.from;
        this.to = builder.to;
        setofBases = builder.setofBases;
    }

    @Override
    public int compareTo(Request another) {
        if (this.base.equals(another.base))return 0 ;

        if (this.base.equals("USD")) return 10; //first we will take USD

        return this.base.compareTo(another.base); // sorting currency like string
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (!baseCurrency.equals(request.baseCurrency)) return false;
        if (!quoteCurrency.equals(request.quoteCurrency)) return false;
        if (date != null ? !date.equals(request.date) : request.date != null) return false;
        if (from != null ? !from.equals(request.from) : request.from != null) return false;
        if (to != null ? !to.equals(request.to) : request.to != null) return false;
        if (!setofBases.equals(request.setofBases)) return false;
        return base.equals(request.base);
    }

    @Override
    public int hashCode() {
        int result = baseCurrency.hashCode();
        result = 31 * result + quoteCurrency.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + setofBases.hashCode();
        result = 31 * result + base.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (base.equals("USD")) return (baseCurrency.equals("USD")) ? quoteCurrency+"," : baseCurrency+",";
        return quoteCurrency + ",";
    }
}
