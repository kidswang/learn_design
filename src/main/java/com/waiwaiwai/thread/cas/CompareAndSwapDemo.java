package com.waiwaiwai.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/23 14:37
 * @Description: CAS工作原理
 */
public class CompareAndSwapDemo {
    int count;
    // 比较并交换
    // 在底层，CAS是一条指令，但不能因为是一条指令就能保证线程安全，需要在交换完成后再判断一下返回的值和我期望的值是否是同一个
    // 例如ABA问题，数字的ABA问题
    // 假设count一开始是A，线程T1再执行完赋值操作后，CAS之前有可能count被线程T2更新成了B
    // 然后线程T3又给更新成了A，这样虽然线程T1看到的是A，但这个A是被更新过后的A
    //
    synchronized int cas(int expect, int newValue) {
        int curValue = count;
        if (curValue == expect) {
            count = newValue;
        }

        AtomicInteger i = new AtomicInteger();
        return curValue;
    }





}
