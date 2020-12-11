package com.waiwaiwai.arithmetic.queue;

import com.waiwaiwai.arithmetic.listnode.ListNode;

import java.util.List;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/4 17:10
 * @Description: 链表队列
 */
public class LinkedQueue {
    private ListNode head = null;
    private ListNode tail = null;

    // 入队
    public void enqueue(int item) {
        if (tail == null) {
            ListNode newNode = new ListNode(item);
            head = newNode;
            tail = newNode;
        } else  {
            tail.next = new ListNode(item);
            tail = tail.next;
        }
    }

    // 出队
    public int dequeue() {
        if (head.next == null) {
            return -1;
        }
        ListNode tmpNode = head.next;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return tmpNode.val;
    }



}
