package com.waiwaiwai.mydesign.bridge.demo;

import org.junit.Test;

public class DemoTest {

    @Test
    public void test() {
        Express express = new SFExpress();
        Notification degree = new UrgencyNotification(express);
        degree.notify("紧急消息");
    }

}
