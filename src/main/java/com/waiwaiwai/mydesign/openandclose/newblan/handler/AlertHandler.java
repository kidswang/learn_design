package com.waiwaiwai.mydesign.openandclose.newblan.handler;

import com.waiwaiwai.mydesign.openandclose.common.Notification;
import com.waiwaiwai.mydesign.openandclose.newblan.ApiStatInfo;
import com.waiwaiwai.mydesign.openandclose.oldblan.AlertRule;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:57
 * @Description: 告警处理抽象类
 */
public abstract class AlertHandler {

    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    /**
     * 核对
     *
     * @param apiStatInfo
     */
    public abstract void check(ApiStatInfo apiStatInfo);

}
