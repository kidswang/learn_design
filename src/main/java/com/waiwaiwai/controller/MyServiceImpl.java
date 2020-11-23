package com.waiwaiwai.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements Runnable, MyService{

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        System.out.println("thread：" + Thread.currentThread().getName());
    }

    @Override
    public void myTest() {
        this.run();
    }
}
