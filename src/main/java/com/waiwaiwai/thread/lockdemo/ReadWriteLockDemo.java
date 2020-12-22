package com.waiwaiwai.thread.lockdemo;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/17 10:22
 * @Description: 读写锁
 */
public class ReadWriteLockDemo {
    // 读锁不能升级成写锁,在读锁还没有释放的时候,就获取写锁,会导致写锁永久等待.
    // 写锁可以降级成读锁,
    // 只有写锁支持条件变量   读锁不支持条件变量 读锁调用newCondition() 会抛出 UnsupportedOperationException 异常。
    Object data;
    volatile boolean cacheValidate;
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();
    private int idx = 0;

    public Object processCachedData() {
        // 读锁
        r.lock();
//        Condition condition = r.newCondition();
//        try {
//            condition.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (!cacheValidate) {
            // 释放读锁
            r.unlock();
            // 获取读锁
            w.lock();

            try {
                if (!cacheValidate) {
                    System.out.println("jin lai le {%d} ci" + ++idx);
                    // 更改data的数据
                    data = new Object();
                    cacheValidate = true;
                }
                // 释放写锁前降级为读锁
                // 读写锁允许降级
                r.lock();
            } finally {
                // 释放写锁
                w.unlock();
            }
        }
        try {
            // 读取使用data
            return data;
        } finally {
            r.unlock();
        }
    }
    @Test
    public void use() throws InterruptedException {
        Object o = processCachedData();
        Thread t1 = new Thread(this::processCachedData);
            Thread t2 = new Thread(this::processCachedData);
            Thread t3 = new Thread(this::processCachedData);
            Thread t4 = new Thread(this::processCachedData);
            Thread t5 = new Thread(this::processCachedData);
            Thread t6 = new Thread(this::processCachedData);
            Thread t7 = new Thread(this::processCachedData);
            Thread t8 = new Thread(this::processCachedData);
            Thread t9 = new Thread(this::processCachedData);
            Thread t0 = new Thread(this::processCachedData);
            t0.start();
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t0.join();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();

    }


}
