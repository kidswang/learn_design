package com.waiwaiwai.arithmetic.listnode;

import cn.hutool.core.date.CalendarUtil;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/27 14:59
 * @Description: 获取链表的中间节点
 */
public class MiddleNode {
    // 链表的中间节点  快慢指针  最后看下是否 指针是节点是奇数个还是偶数个   奇数个返回一个节点 偶数个返回两个节点
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
//        List<String> nowDateList = CalendarUtil.findDatesList(CalendarUtil.getBeginDayOfWeek(), CalendarUtil.getEndDayOfWeek());//本周日期列表
//        List<String> nextDateList = CalendarUtil.findDatesList(CalendarUtil.getWeek(1), CalendarUtil.getNextWeekMonday(CalendarUtil.getWeek(1)));//下周日期列表
//        ListNode head = new ListNode(1);
//        head.next = null;
//        ListNode listNode = middleNode(head);
//        System.out.println(listNode);

//        BigDecimal b = new BigDecimal(0);
//        System.out.println(b.c(new BigDecimal(0)));

//        BigDecimal b = new BigDecimal(1);
//        BigDecimal c = new BigDecimal(3);
//        System.out.println(b.divide(c, 4, BigDecimal.ROUND_HALF_UP));
//
//        System.out.println(BigDecimal.ONE);
//        System.out.println(BigDecimal.TEN);
//
//        BigDecimal b = new BigDecimal(0.00);
//        BigDecimal c = new BigDecimal(0.00);
//        BigDecimal add = b.add(c);
//        System.out.println(add.equals(BigDecimal.ZERO));

//        List<String> list = new ArrayList<>();
//        String str = "2020-11-30";
//        list.add(str);
//        for (String s : list) {
//            s.substring(0, s.lastIndexOf("-"));
//        }
//        System.out.println(str.substring(0, str.lastIndexOf("-")));
//        System.out.println(list.toString());
// radix
        String i = "";
        int i1 = (int)Double.parseDouble("0");
//        Integer integer = Integer.valueOf(i);
//        Integer integer = Integer.getInteger(i);
        System.out.println(i1);

    }

}
