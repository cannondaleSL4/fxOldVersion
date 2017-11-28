package com.dim.fxapp.request.controller;

import com.dim.fxapp.request.impl.RequestFromServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/test")
    public String home() {
        RequestFromServer requestFromServer = new RequestFromServer();
        requestFromServer.sendLiveRequest();
        return "Hello Docker World";
    }
}