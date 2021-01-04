package com.waiwaiwai.mydesign.bridge.demo;

public class SevereNotification extends Notification {

    public SevereNotification(Express express) {
        super(express);
    }

    @Override
    void notify(String msg) {
        express.send(msg);
    }
}
