package com.waiwaiwai.mydesign.openandclose.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:28
 * @Description: 出发告警的规则
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private Long maxTps;
    private Long maxErrorCount;
    /**
     *  添加功能需要做的改动， 添加超时阈值
     */
    private Long maxTimeoutTps;

}
