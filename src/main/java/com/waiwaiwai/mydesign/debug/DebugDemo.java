package com.waiwaiwai.mydesign.debug;

import org.junit.Test;

public class DebugDemo {

    @Test
    public void test1() {
        this.method1();
        System.out.println("方法都执行完后会执行这里");
    }

    private void method1() {
        Integer i1 = this.method2();
        Integer i2 = this.method2();
        DebugMethod debugMethod = new DebugMethod();
        debugMethod.testCursor();
        System.out.println("第一个方法");
    }

    private Integer method2() {
        int i = 123;
        System.out.println("第二个方法");
        return i;
    }


}
