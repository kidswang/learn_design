package com.waiwaiwai.mydesign.bridge.demo;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 10:33
 * @Description: 紧急程度
 */
public abstract class Notification {
    protected Express express;

    public Notification(Express express) {
        this.express = express;
    }

    abstract void notify(String msg);

}
