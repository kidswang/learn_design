package com.waiwaiwai.thread;

import org.junit.Test;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/10 9:26
 * @Description: 生产者消费者模型
 */

public class BlockedQueue<T> {

    List<String> list = new ArrayList<>(10);
    final Lock lock =
            new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    @Test
    public void test() throws InterruptedException {
        BlockedQueue<String> queue = new BlockedQueue<>();

        Thread t1 = new Thread(() -> {
            try {
                queue.deq();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                queue.deq();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                queue.enq("123");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    // 入队
    void enq(T x) throws InterruptedException {
        lock.lock();
        System.out.println("======================");
        try {
            while (list.size() > 10) {
                // 等待队列满了
                notFull.await();
            }
            list.add(x.toString());
            // 省略入队操作...
            //入队后,通知可出队
            System.out.println("wo ru dui le ");
            notEmpty.signal();
        } finally {
            System.out.println("chu dui jie suo le");
            lock.unlock();
        }
    }

    // 出队
    void deq() throws InterruptedException {
        lock.lock();

        System.out.println("------------------------");
        System.out.println(Thread.currentThread().getName());
        try {
            while (list.isEmpty()) {
                System.out.println("wo jin lai le");
                // 等待队列不空
                notEmpty.await(); // 让出持有的锁  线程在原地等待 该等待队列有序  先进先出
            }
            // 省略出队操作...
            //出队后，通知可入队
            System.out.println(Thread.currentThread().getName());
            System.out.println("我出队了");
            notFull.signal();
        } finally {
            System.out.println("ru dui jie suo le");
            lock.unlock();
        }
    }
}
