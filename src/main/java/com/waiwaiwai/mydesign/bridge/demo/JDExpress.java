package com.waiwaiwai.mydesign.bridge.demo;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 10:27
 * @Description: 京东快递
 */
public class JDExpress implements Express{

    @Override
    public void send(String msg) {
        System.out.println("京东快递发出了..." + msg);
    }
}
