package com.waiwaiwai.mydesign.bridge.demo;

public class NormalNotification extends Notification {

    public NormalNotification(Express express) {
        super(express);
    }

    @Override
    void notify(String msg) {
        express.send(msg);
    }
}
