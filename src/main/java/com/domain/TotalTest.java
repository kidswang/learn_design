package com.domain;

import org.junit.Test;

public class TotalTest {


    @Test
    public void test5() {
//        int z = 'z';
//        char c = '0' - 1;
//        System.out.println(c);
//        System.out.println(z);
        String str = "fdsaf";
        System.out.println(str.indexOf("d"));

    }

    @Test
    public void test4() {
//        Integer i1 = new Integer(20);
//        Integer i2 = new Integer(20);
//        System.out.println(i1 == i2); // false
//
//        Integer i3 = new Integer(200);
//        Integer i4 = new Integer(200);
//        System.out.println(i3 == i4); // false

        // -128 - 127 都是用的缓存里面的值
        Integer i5 = 30;
        Integer i6 = 30;
        System.out.println(i5 == i6); // true
        Integer i7 = 300;
        Integer i8 = 300;
        System.out.println(i7 == i8); // false
    }


    @Test
    public void test3() {
        Thread th = Thread.currentThread();
        int i = 0;
        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1);
                    th.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        while (true) {
            if (th.isInterrupted()) {
                break;
            }
            System.out.println(++i);
            // 省略业务代码无数
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2() {
//        double log = Math.log(4);
//        System.out.println(log);
//        Double d = 10d;
//
//        d = d / 3 ;
//        int i = d.intValue();
//        System.out.println(i);

        String str = "20";

        double v = Double.parseDouble(str);
        int i = Double.valueOf(str).intValue();

    }

    @Test
    public void test() {
//        Integer i1 = 3;
//        Integer i2 = 2;
//        // 小于 -1
//        // 等于 0
//        // 大于 1
//        System.out.println(i1.compareTo(i2));

        double d1 = 1;
        double d2 = 3;
        System.out.println(d1 / d2);

    }

}
