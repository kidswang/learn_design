package com.waiwaiwai.mydesign.openandclose.oldblan;

import com.waiwaiwai.mydesign.openandclose.common.AlertRule;
import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.common.NotificationEmergencyLevel;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:09
 * @Description: 警告提示类
 *
 * update: 添加一个功能，当每秒钟接口超时请求个数大于预先设定的阈值，触发告警
 *
 */

public class OldAlert {

    private AlertRule rule;
    private Notification notification;

    public OldAlert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    // 当接口的TPS超过某个预定的阈值以及当接口请求出错数大于预定的阈值就会出发警告

    /**
     *
     * @param api 具体uri
     * @param requestCount 访问的次数
     * @param errorCount 错误的次数
     * @param durationOfSeconds 持续的时间
     *
     * update: 添加功能做的改动，添加 timeoutCount
     * @param timeoutCount 超时次数
     */
    public void check(String api, long requestCount, long errorCount, long durationOfSeconds, Long timeoutCount) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
        // 添加接口超时处理逻辑
        Long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutCount >  rule.getMatchedRule(api).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }

}
