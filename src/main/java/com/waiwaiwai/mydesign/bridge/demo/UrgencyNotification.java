package com.waiwaiwai.mydesign.bridge.demo;

public class UrgencyNotification extends Notification {

    public UrgencyNotification(Express express) {
        super(express);
    }

    @Override
    void notify(String goods) {
        express.send(goods);
    }
}
