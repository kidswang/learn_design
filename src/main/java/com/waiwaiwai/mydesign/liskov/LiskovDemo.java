package com.waiwaiwai.mydesign.liskov;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/15 9:30
 * @Description: 里氏替换原则
 */
public class LiskovDemo {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.func1(6, 5));

        B b = new B();
        System.out.println(b.func1(6, 5));
        System.out.println(b.func2(6,5));
    }



}

class A {
    int func1(int a, int b) {
        return a + b;
    }
}

class B extends A {
    int func1(int a, int b) {
        return a - b;
    }

    int func2(int a, int b) {
        return func1(a, b) + 9;
    }

}

