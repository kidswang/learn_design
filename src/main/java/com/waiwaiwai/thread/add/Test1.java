package com.waiwaiwai.thread.add;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

public class Test1 {
    private AtomicLong count = new AtomicLong(0);
//    private Long count = 0L;
    private void add10K() {
        int idx = 0;
        while(idx++ < 1000000) {
            count.addAndGet(1);
//            count++;
        }
    }
    @Test
    public void calc() throws InterruptedException {
        final Test1 test = new Test1();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(test::add10K);
        Thread th2 = new Thread(test::add10K);
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
        return;
    }
}