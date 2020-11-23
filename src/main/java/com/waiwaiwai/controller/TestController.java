package com.waiwaiwai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/get")
    public String test() {
        myService.myTest();
        return "sss";
    }
}
