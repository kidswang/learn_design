package com.waiwaiwai.mydesign.single;

import org.junit.Test;

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


}
