package com.waiwaiwai.mydesign.bridge.demo;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 10:27
 * @Description: 邮政快递
 */
public class PostalExpress implements Express{

    @Override
    public void send(String msg) {
        System.out.println("邮政快递发出了..." + msg);
    }
}
