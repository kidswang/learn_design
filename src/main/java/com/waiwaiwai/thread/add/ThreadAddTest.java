package com.waiwaiwai.thread.add;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadAddTest {

    private int i = 0;

    @Test
    public void test2() throws InterruptedException {
        // 栈  栈帧  栈帧中的  局部变量表  操作数栈  方法返回地址  动态链接
        int abs = Math.abs(-1);
        System.out.println(abs);


    }


    @Test
    public void test1() throws InterruptedException {

        Runnable target;
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            i = i+10;
        });

        i = 6;

        t1.start();
        Thread.sleep(500);
        i = i+100;
        Thread.sleep(2000);
        System.out.println(i);
    }


    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        TestAdd testAdd = new TestAdd();
//        executorService.execute(new Thread(() -> testAdd.add10K()));
//        executorService.execute(new Thread(() -> testAdd.add10K()));
        Thread t1 = new Thread(() -> testAdd.add10K());
        Thread t2 = new Thread(() -> testAdd.add10K());
//        executorService.execute(t1);
//        executorService.execute(t2);
//        executorService.submit(t1);
//        executorService.submit(t2);
//        executorService.awaitTermination(5, TimeUnit.SECONDS);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(testAdd.count);
        System.out.println("jieshu");
    }


    private static class TestAdd {
        private long count = 0;
        private void add10K() {
            int idx = 0;
            while (idx++ < 100000000) {
                count += 1;
            }
        }
    }


}

