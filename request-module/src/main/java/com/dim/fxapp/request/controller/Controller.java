package com.dim.fxapp.request.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dima on 18.11.17.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/test")
    public String home() {
        return "Hello Docker World";
    }
}