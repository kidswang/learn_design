package com.waiwaiwai.mydesign.observable.template;

import java.util.concurrent.TimeUnit;

public class ConcreteObservableTwo implements Observable{
    @Override
    public void update(String msg) {
        try {
            // 假装需要14秒完成操作
            TimeUnit.SECONDS.sleep(14);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two observable");
    }
}




