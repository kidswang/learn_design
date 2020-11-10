package com.waiwaiwai.mydesign.openandclose.newblan.handler;

import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.common.NotificationEmergencyLevel;
import com.waiwaiwai.mydesign.openandclose.newblan.ApiStatInfo;
import com.waiwaiwai.mydesign.openandclose.oldblan.AlertRule;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 11:06
 * @Description: TPS告警处理类
 */
public class TpsAlertHandler extends AlertHandler{
    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/ apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "TPS");
        }
    }
}
