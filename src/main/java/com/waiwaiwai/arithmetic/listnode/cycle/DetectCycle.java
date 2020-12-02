package com.waiwaiwai.arithmetic.listnode.cycle;

import com.waiwaiwai.arithmetic.listnode.ListNode;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/26 17:10
 * @Description: 链表环的检测
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        // 如果使用双指针  快慢指针重合说明有环
        // 快慢指针在有环的情况下  一定在会在一个地方相逢  他们相逢时
        // 快指针比慢指针多走了环长度的整数倍，而且快指针比慢指针多走的和慢指针走的一样
        // 所以慢指针也是走了环长度的整数倍
        // 现在让快指针从头开始走,速度和慢指针一样,这样快慢指针会在环入口相遇
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

}
