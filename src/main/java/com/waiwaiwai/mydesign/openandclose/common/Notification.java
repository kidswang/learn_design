package com.waiwaiwai.mydesign.openandclose.common;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:11
 * @Description: 告警通知类
 */
public class Notification {
    /**
     *
     * @param notificationEmergencyLevel 紧急程度
     * @param msg
     */
    public void notify(String notificationEmergencyLevel, String msg) {
        System.out.println("假装这是发邮件 紧急程度: "+notificationEmergencyLevel + ", " + msg);
    }

}
