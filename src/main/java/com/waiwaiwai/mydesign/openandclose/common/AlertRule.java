package com.waiwaiwai.mydesign.openandclose.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.waiwaiwai.mydesign.openandclose.common.Rule;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/10 10:10
 * @Description: 存储警告规则
 */

public class AlertRule {

    public Rule getMatchedRule(String api) {
        return new Rule(10L, 20L, 10L);
    }

    @Test
    public void test() {

        Person person1 = new Person();
        person1.setClassId("1");
        person1.setGradeId("1");

        Person person2 = new Person();
        person2.setClassId("2");
        person2.setGradeId("1");

        Person person3 = new Person();
        person3.setClassId("3");
        person3.setGradeId("1");


        List<Person> list = new ArrayList();

        list.add(person1);
        list.add(person2);
        list.add(person3);

    }

    @Test
    public void test1() {
        String windowStr = "1,2,3,4,5,10,11,12,18,20,25,33";
//        String[] windows = {"1", "3", "4", "5", "8", "9", "10", "12", "25"};
        String[] windows =windowStr.split(",");
        String moving = "";
        if(windows.length>1){
            String str = new String();
            String firstStr = ""; // 获取第一个str
            for (int i = 0; i < windows.length; i++) {
                if (str.length() == 0) {
                    firstStr= windows[i];
                    str = str + windows[i];
                } else {
                    String thisWindow = windows[i];
                    if (Integer.valueOf(windows[i-1]) + 1 == Integer.valueOf(thisWindow)) { //转换为数字比对+1后是否相等
                        str = str + windows[i];
                    } else {
                        if (str.length() > windows[i-1].length() && !str.equals(thisWindow)) {
                            str = firstStr + "-" + windows[i-1];
                        }
                        moving+=str+",";

                        // 添加后重置
                        str = "";
                        firstStr= windows[i];
                        str = str + windows[i];
                    }

                    if (i == windows.length-1) {// 最后一个字符时, 进行处理
                        if (str.length() > 1 && !str.equals(thisWindow)) {
                            str = firstStr + "-" + thisWindow;
                        }
                        moving+=str;
                    }
                }
            }
        }else{
            moving=windowStr;
            if (moving.length() > 1) {
                moving = moving.substring(0, moving.length() - 1);
            }
        }
    }

    @Test
    public void test3() {

    }
    @Test
    public void test5() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setId("qw");
        p1.setGradeId("er");

        Person p2 = new Person();
        p2.setId("zx");
        p2.setGradeId("er");

        Person p3 = new Person();
        p3.setId("cv");
        p3.setGradeId("er");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.stream().forEach(item -> {
            item.setId("qweqwe");
        });

        System.out.println(list);

    }


}
