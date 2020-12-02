package com.waiwaiwai.arithmetic.listnode;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/27 14:29
 * @Description: 两个有序链表的合并
 */
public class MergeTwoListNode {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode returnNode = null;
        ListNode prev = returnNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        if (l1 != null) {
            prev.next = l1;
        }
        if (l2 != null) {
            prev.next = l2;
        }

        return returnNode;
    }

}


