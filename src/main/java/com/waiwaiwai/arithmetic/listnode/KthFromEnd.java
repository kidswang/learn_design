package com.waiwaiwai.arithmetic.listnode;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/27 14:49
 * @Description: 删除倒数第K个节点
 */
public class KthFromEnd {

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        while (fast != null) {
            // 既然是从倒数的第K个节点开始删除
            // 那么我使用两个指针   一个指针直接走  另外一个指针等第一个指针走完要从哪里删除数的步数后再走
            // 这样慢指针肯定会剩余要从第几个删除数量的节点
            fast = fast.next;
            if (k == 0) {
                head = head.next;
            } else {
                k--;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        getKthFromEnd(l1,1);
        System.out.println("12312");
    }

}
