package com.dim.fxapp.gateway.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by dima on 19.11.17.
 */
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Using Post PreFilter");

        return null;
    }
}
