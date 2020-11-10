package com.waiwaiwai.mydesign.openandclose.newblan.handler;

import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.common.NotificationEmergencyLevel;
import com.waiwaiwai.mydesign.openandclose.newblan.ApiStatInfo;
import com.waiwaiwai.mydesign.openandclose.common.AlertRule;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 11:14
 * @Description: 错误告警处理类
 */
public class ErrorAlertHandler extends AlertHandler{
    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "错误");
        }
    }
}
