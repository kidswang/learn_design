package com.waiwaiwai.mydesign.prototype;

import com.waiwaiwai.mydesign.openandclose.common.Person;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 11:07
 * @Description: 浅拷贝
 */
public class ShallowCopyDemo {


    @Test
    public void test3() {
        double l = 123.23;
        double r = 123.23;
        System.out.println(l == r);

        double d = 1 / 3;
        double e = 1 / 3;

        System.out.println(d == e);
    }


    @Test
    public void test2() {
        Person p1 = new Person();
        p1.setId("1");
        Person p2 = new Person();
        BeanUtils.copyProperties(p1, p2);

        Person p3 = p1;

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1 == p3);
    }

    @Test
    public void test() {
        int[] i3 = {1, 3, 5};
        int[] i33 = i3.clone();
        i33[0] = 11;
        // 如果是基本数据类型， 会复制基本数据类型中的值。clone中的数据变了，原来的数据源中的值不会变。
        System.out.println(i3[0] + "==" + i33[0]);

        // 二维数组，clone中的数据变了，原来的数据源中的值也会跟着变。
        // 因为二维数据中，存储的是一个个的对象，对于对象来说，只是复制了内存地址
        int[][] i4 = {{1, 5}, {2, 6}};
        int[][] i44 = i4.clone();

        i44[0][0] = 12;
        System.out.println(i4[0][0] + "====" + i44[0][0]);
    }

}
