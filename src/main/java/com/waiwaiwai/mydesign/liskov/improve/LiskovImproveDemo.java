package com.waiwaiwai.mydesign.liskov.improve;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/15 9:34
 * @Description: 里氏替换原则改进
 */
public class LiskovImproveDemo {

    public static void main(String[] args) {
        B b = new B(new A());
        System.out.println(b.func3(1, 2));
    }

}


class Base {

}

class A extends Base{
    int func1(int a, int b) {
        return a + b;
    }
}

class B extends Base {
    private A a;

    public B(A a) {
        this.a = a;
    }

    int func1(int a, int b) {
        return a - b;
    }

    int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}