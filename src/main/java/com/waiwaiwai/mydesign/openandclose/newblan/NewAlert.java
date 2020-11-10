package com.waiwaiwai.mydesign.openandclose.newblan;

import com.waiwaiwai.mydesign.openandclose.newblan.handler.AlertHandler;
import com.waiwaiwai.mydesign.openandclose.oldblan.AlertRule;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:56
 * @Description: 对之前Alert告警的重构
 */
public class NewAlert {

    private List<AlertHandler> alertHandlers = new ArrayList<>();
    public void addAlertHandler(AlertHandler alertHandler) {
        alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler alertHandler : alertHandlers) {
            alertHandler.check(apiStatInfo);
        }
    }

}
