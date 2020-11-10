package com.waiwaiwai.mydesign.openandclose.newblan.handler;

import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.common.NotificationEmergencyLevel;
import com.waiwaiwai.mydesign.openandclose.newblan.ApiStatInfo;
import com.waiwaiwai.mydesign.openandclose.common.AlertRule;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 11:30
 * @Description: 超时处理类
 */
public class TimeoutAlertHandler extends AlertHandler{

    public TimeoutAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tpsTimeOut = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (tpsTimeOut > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "超时");
        }
    }
}
