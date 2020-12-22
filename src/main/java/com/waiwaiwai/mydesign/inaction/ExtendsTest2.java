package com.waiwaiwai.mydesign.inaction;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ExtendsTest2 extends ExtendsTest{

    @Test
    public void test() {
        ExtendsTest2 test2 = new ExtendsTest2();
        ExtendsTest test1 = new ExtendsTest();
        List<String> strList = Collections.emptyList();
        for (String s : strList) {
            System.out.println(s);
        }
        List<String> list = null;
        if (list == null) list = Collections.emptyList();
        for (String s : list) {

        }

        System.out.println(test1);
    }

}
