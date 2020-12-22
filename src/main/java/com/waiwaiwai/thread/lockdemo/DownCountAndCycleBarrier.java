package com.waiwaiwai.thread.lockdemo;

import org.junit.Test;

import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.*;


/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/17 14:25
 * @Description:
 */
public class DownCountAndCycleBarrier {
    // DownCount 减数器，主要用于一个线程等待多个线程执行结束后才执行 不能重复使用
    // cycleBarrier 是一组线程之间互相等待  可以重复使用
    Executor executor = Executors.newFixedThreadPool(2);
    final CountDownLatch countDownLatch = new CountDownLatch(2);

    //
    @Test
    public void DownCountTest() throws InterruptedException {
        executor.execute(() -> {
            System.out.println("1111");
            countDownLatch.countDown();
        });
        executor.execute(() -> {
            System.out.println("2222");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println("3333333333");
    }

    // 订单队列
    Vector<Object> pos = new Vector<>();
    // 派送单队列
    Vector<Object> dos = new Vector<>();
    // 多个线程可能会出现问题
    Executor e = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier = new CyclicBarrier(2, () -> e.execute(this::check));

    @Test
    public void CycleBarrierTest()  {
        while (true) {
            Object o = new Object();
            executor.execute(() -> {
                pos.add(o);
                try {
                    barrier.await();
                    System.out.println("cishu");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            executor.execute(() -> {
                dos.add(o);
                try {
                    barrier.await();
                    System.out.println("cishu2");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private boolean check() {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }
        Object o1 = pos.get(0);
        Object o2 = dos.get(0);
        System.out.println(Thread.currentThread().getName() + " " + Objects.equals(o1, o2));

        pos.remove(0);
        dos.remove(0);
        return false;
    }


}
