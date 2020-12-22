package com.waiwaiwai.mydesign.build;

import org.apache.commons.lang3.StringUtils;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/18 13:21
 * @Description: 建造者模式
 * 适用于类的属性之间存在一定的依赖或约束条件
 */
public class ResourceConfigBuilder {

    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;
    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourceConfig build() {
        // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("...");
        }
        if (maxIdle > maxTotal) {
            throw new IllegalArgumentException("...");
        }
        if (minIdle > maxTotal || minIdle > maxIdle) {
            throw new IllegalArgumentException("...");
        }
        ResourceConfig resourceConfig = new ResourceConfig(name, maxTotal, maxIdle, minIdle);
        return resourceConfig;
    }

    public ResourceConfigBuilder setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("...");
        }
        this.name = name;
        return this;
    }

    public ResourceConfigBuilder setMaxTotal(int maxTotal) {
        if (maxTotal <= 0) {
            throw new IllegalArgumentException("...");
        }
        this.maxTotal = maxTotal;
        return this;
    }

    public ResourceConfigBuilder setMaxIdle(int maxIdle) {
        if (maxIdle < 0) {
            throw new IllegalArgumentException("...");
        }
        this.maxIdle = maxIdle;
        return this;
    }

    public ResourceConfigBuilder setMinIdle(int minIdle) {
        if (minIdle < 0) {
            throw new IllegalArgumentException("...");
        }
        this.minIdle = minIdle;
        return this;
    }

}
