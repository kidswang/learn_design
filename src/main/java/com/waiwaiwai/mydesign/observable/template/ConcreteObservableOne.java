package com.waiwaiwai.mydesign.observable.template;

import java.util.concurrent.TimeUnit;

public class ConcreteObservableOne implements Observable{
    @Override
    public void update(String msg) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one observable");
    }
}
