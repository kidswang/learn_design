package com.waiwaiwai.controller;

import com.waiwaiwai.drow.ResponseDemo4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = "/getOne")
    public void getOne(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResponseDemo4 demo4 = new ResponseDemo4();
        demo4.doPost(request, response);
    }

}
