package com.waiwaiwai.mydesign.proxy.proxyimprove;

import com.waiwaiwai.mydesign.proxy.IUserController;
import com.waiwaiwai.mydesign.proxy.UserController;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 16:41
 * @Description: 动态代理类
 */
public class MetricsCollectorProxy {

    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    // 使用
    @Test
    public void test() {
        MetricsCollectorProxy metricsCollectorProxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) metricsCollectorProxy.createProxy(new UserController());
        userController.login("123", "fsfs");
    }

}
