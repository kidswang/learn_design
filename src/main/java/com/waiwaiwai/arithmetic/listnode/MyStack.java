package com.waiwaiwai.arithmetic.listnode;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/2 15:26
 * @Description: 自定义一个链表栈
 */
public class MyStack {

    private Node top;

    // push
    public void push(int value) {
        Node newNode = new Node(value, null);

        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    // pop
    public int pop() {
        if (top == null) return -1;
        int data = top.data;
        top = top.next;
        return data;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.getData() + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
