package com.waiwaiwai.arithmetic.listnode;

import org.springframework.util.StringUtils;

/**
 * 查询是否是回文字符串
 */
public class PlalindromeStr {

    public static boolean pla(String str) {
        // 一个字符串是否是回文?
        if (StringUtils.isEmpty(str) || str.length() == 1) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        // 替换掉所有的空格和符号
        str = str.toLowerCase();
        char[] chars = str.toCharArray();
        int index = 0;
        for (char c : chars) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                sb.append(c);
            }
        }
        char[] charr = sb.toString().toCharArray();
        boolean flag = true;
        int mid = charr.length / 2;
        for (int i = 0; i < charr.length; i++) {
            if (i == mid) {
                break;
            }
            if (charr[i] != charr[charr.length - i - 1]) {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean plaDouble(String s) {
        s = s.toLowerCase();
        int pre = 0;
        int behind = s.length() -1;
        char[] chars = s.toCharArray();
        while (pre < behind) {
            if ((!(chars[pre] >= '0' && chars[pre] <= '9') && !(chars[pre] >= 'a' && chars[pre] <= 'z'))) {
                pre++;
                continue;
            }
            if ((!(chars[behind] >= '0' && chars[behind] <= '9') && !(chars[behind] >= 'a' && chars[behind] <= 'z'))) {
                behind--;
                continue;
            }

            if (chars[pre] != chars[behind]) {
                return false;
            }
            pre ++;
            behind --;
        }

        return true;
    }

    // 字符串链表  判断是否是回文
    // 使用快慢指针
    public static boolean isPla(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            // 颠倒前一半链表的顺序
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // 存在链表大小时奇数的情况.
        if (slow.next != null) {
            slow = slow.next;
        }
        //
        while (slow.next != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }

    public static void main(String[] args) {
//        String str = "qw,,  a,,a,  ..,wq";
//        System.out.println(pla(str));
//
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        test(l1);
//        reserveNode(l1);
//        System.out.println(isPla(l1));
//        String str = "race a car";
//        boolean b = plaDouble(str);
//        System.out.println(b);
    }

    public static void test(ListNode head) {
//        ListNode l1 = head;
//        l1.next = l1.next.next;
//        System.out.println(l1);

        ListNode l100 = new ListNode(100);
        ListNode l1 = head;
        l100.next = l1.next;
        l1.next = l100;
        System.out.println(l1);
    }

    public static ListNode reserveNode(ListNode head) {
        // 链表反转
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


}

