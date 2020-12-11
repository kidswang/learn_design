package com.waiwaiwai.arithmetic.queue;

import java.util.ArrayList;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/4 17:24
 * @Description: 数组队列
 */
public class ArrayQueue {

    String[] items = null;
    // 数组大小
    int n = 0;
    int head = 0;
    int tail = 0;

    // 初始化数组大小
    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    // 入队列
    public boolean enqueue(String item) {
        if (n == tail) {
            if (head == 0) return false;
            for (int i = head; i < tail; i++) {
                items[i-head] = items[i];
            }
            tail = tail - head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    // 出队列
    public String dequeue() {
        if (head == tail) return null;
        String ret = items[head];
        head++;
        return ret;
    }


}
