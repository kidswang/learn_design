package com.waiwaiwai.mydesign.single;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/16 16:55
 * @Description: double check
 */
public class SingleTest {

    static volatile SingleTest instance;

    static SingleTest getInstance() {
        if (instance == null) {
            synchronized (SingleTest.class) {
                if (instance == null) {
                    System.out.println("jin lai le o");
                    instance = new SingleTest();
                }
            }
        }
        return instance;
    }


//    @Test
//    public void test() throws InterruptedException {
public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(SingleTest::getInstance);
        Thread t2 = new Thread(SingleTest::getInstance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    @Test
    public void test() {
        long l = System.nanoTime();
        System.out.println(l);
    }

    @Test
    public void test3() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + "application.yml");
        URL resource = this.getClass().getResource("/");
        System.out.println(resource.toString());
//        System.out.println(Objects.isNull(resourceAsStream));
    }


}
