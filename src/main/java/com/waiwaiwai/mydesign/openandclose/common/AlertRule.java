package com.waiwaiwai.mydesign.openandclose.common;

import com.waiwaiwai.mydesign.openandclose.common.Rule;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:10
 * @Description: 存储警告规则
 */

public class AlertRule {

    public Rule getMatchedRule(String api) {
        return new Rule(10L, 20L, 10L);
    }

}
