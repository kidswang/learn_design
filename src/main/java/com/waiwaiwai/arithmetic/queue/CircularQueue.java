package com.waiwaiwai.arithmetic.queue;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/4 17:58
 * @Description: 循环队列
 */
public class CircularQueue {
    String[] items = null;
    int n = 0;
    int head = 0;
    int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) return false;
        
        return true;
    }


}
