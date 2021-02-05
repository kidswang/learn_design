package com.waiwaiwai.mydesign.observable.template;

import org.junit.Test;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/5 13:28
 * @Description: 使用观察者模式
 */
public class ObservableDemo {

    @Test
    public void test() {
        ConcreteSubject concreteSubject = new ConcreteSubject();
        concreteSubject.registerObserver(new ConcreteObservableOne());
        concreteSubject.registerObserver(new ConcreteObservableTwo());
        concreteSubject.notifyObservers("ssss");
    }

}
