package com.waiwaiwai.mydesign.proxy.proxyimprove;

import com.waiwaiwai.mydesign.prjaction1.improve.RequestInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxiedObject;

    public DynamicProxyHandler(Object proxiedObject) {
        this.proxiedObject = proxiedObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用前的处理逻辑
        long startTimestamp = System.currentTimeMillis();
        // 这里是调用委托的方法
        Object result = method.invoke(proxiedObject, args);
        // 调用后的处理逻辑
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
        RequestInfo requestInfo = new RequestInfo(apiName, responseTime, startTimestamp);
        MetricsCollector metricsCollector = null;
        metricsCollector.recordRequest(requestInfo);
        return result;
    }
}
