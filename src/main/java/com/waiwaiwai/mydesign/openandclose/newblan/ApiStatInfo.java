package com.waiwaiwai.mydesign.openandclose.newblan;

import lombok.Data;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:59
 * @Description: api状态信息类
 */
@Data
public class ApiStatInfo {
    /**
     * uri
     */
    private String api;
    /**
     * 请求次数
     */
    private Long requestCount;
    /**
     * 错误次数
     */
    private Long errorCount;
    /**
     * 持续时间
     */
    private Long durationOfSeconds;
    // 改动
    /**
     * 超时数量
     */
    private Long timeoutCount;
}
