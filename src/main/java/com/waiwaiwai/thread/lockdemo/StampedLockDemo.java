package com.waiwaiwai.thread.lockdemo;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/17 13:18
 * @Description: 乐观读
 */
public class StampedLockDemo {
    // 乐观读是无锁的
    final StampedLock sl = new StampedLock();
    private int x, y;

    public int distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        int curX = x;
        int curY = y;
        // 判断在读的时候数据是否已经发生了改变
        if (!sl.validate(stamp)) {
            // 如果修改过
            // 升级为悲观锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(curX * curX + curY * curY);
    }

    final StampedLock lock = new StampedLock();

    /**
     * 尝试中断
     */
    @Test
    public void test() throws InterruptedException {
        Thread T1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 永远阻塞在此处，不释放写锁
            LockSupport.park();
        });
        T1.start();
        // 保证T1获取写锁
        Thread.sleep(100);
        Thread T2 = new Thread(() ->
                // 如果要中断，使用可中断的悲观读锁和写锁
                // lock.readLockInterruptibly();

                //阻塞在悲观读锁
                lock.readLock()
                );
        T2.start();
        // 保证T2阻塞在读锁
        Thread.sleep(100);
        //中断线程T2//会导致线程T2所在CPU飙升
        T2.interrupt();
        T2.join();
    }


}
