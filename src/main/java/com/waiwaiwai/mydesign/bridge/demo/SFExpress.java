package com.waiwaiwai.mydesign.bridge.demo;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 10:27
 * @Description: 顺丰快递
 */
public class SFExpress implements Express {

    @Override
    public void send(String msg) {
        System.out.println("顺丰快递发出了..." + msg);
    }
}
