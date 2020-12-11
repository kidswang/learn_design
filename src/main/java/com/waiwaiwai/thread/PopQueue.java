package com.waiwaiwai.thread;

import org.junit.Test;

public class PopQueue {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            Runnable target;
            Thread t1 = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
        }


    }


}
