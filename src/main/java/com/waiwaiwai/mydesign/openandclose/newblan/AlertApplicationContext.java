package com.waiwaiwai.mydesign.openandclose.newblan;

import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.newblan.handler.ErrorAlertHandler;
import com.waiwaiwai.mydesign.openandclose.newblan.handler.TimeoutAlertHandler;
import com.waiwaiwai.mydesign.openandclose.newblan.handler.TpsAlertHandler;
import com.waiwaiwai.mydesign.openandclose.common.AlertRule;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 11:19
 * @Description: 操作alert的类
 */
public class AlertApplicationContext {

    private AlertRule alertRule;
    private Notification notification;
    private NewAlert alert;




    public void initializeBeans() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new NewAlert();
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        // 改动
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
    }

    public NewAlert getAlert() {
        return alert;
    }

    /**
     *  饿汉式单例
     */
    public static final AlertApplicationContext instance = new AlertApplicationContext();

    private AlertApplicationContext() {
        initializeBeans();
    }

    public static AlertApplicationContext getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        apiStatInfo.setApi("www.yyy.com");
        apiStatInfo.setDurationOfSeconds(1L);
        apiStatInfo.setErrorCount(50L);
        apiStatInfo.setRequestCount(100L);
        apiStatInfo.setTimeoutCount(100L);
        AlertApplicationContext instance = AlertApplicationContext.getInstance();
        instance.getAlert().check(apiStatInfo);
    }

}
