package com.domain;

import org.junit.Test;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class TotalTest {

    private final String SSs = "这是%s,只是%s";


    @Test
    public void test15() {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusSeconds(1));

//        String format = String.format(SSs, "零", "壹");
//        System.out.println(format);

//        String str = "http://27.221.57.109:9000/equmanagement/2021/1/12/123_1610336057951.docx";
//        String[] split = str.split("\\.");
//        System.out.println(split[split.length - 1]);

    }

    @Test
    public void test14() {
        String s1 = "1";
        String s2 = "12";

        System.out.println(s1.matches("^\\d$"));
        System.out.println(s2.matches("^\\d$"));



//        String str = "\nfsdljl\ndocx";
//        if (str.startsWith("\n")) {
//            String replace = str.replaceFirst("\n", "");
//            System.out.println(replace);
//        String[] split = replace.split("\n");
//        System.out.println(split.length);
//        }
//        System.out.println(str.contains(".doc"));

//        System.out.println(str.lastIndexOf(".docx"));
    }


    @Test
    public void test13() throws UnsupportedEncodingException {

        System.out.println("");
        String str = "admin@山东省青岛第六中学";
        String encode1 = UriEncoder.encode(str); // admin@%E5%B1%B1%E4%B8%9C%E7%9C%81%E9%9D%92%E5%B2%9B%E7%AC%AC%E5%85%AD%E4%B8%AD%E5%AD%A6

        System.out.println(encode1);

//        String encodeUserId = URLEncoder.encode(str, "utf-8"); //admin%40%E5%B1%B1%E4%B8%9C%E7%9C%81%E9%9D%92%E5%B2%9B%E7%AC%AC%E5%85%AD%E4%B8%AD%E5%AD%A6
//        String decode1 = URLDecoder.decode(str, "utf-8");
//        System.out.println(encodeUserId);
//        System.out.println(decode1);
//        String decode = URLDecoder.decode(encodeUserId, "utf-8");
//        System.out.println(decode);
//        String encode = URLEncoder.encode(str, "utf-8");
//        System.out.println(encode);
//        String[] split = str.split("\n");
//        System.out.println(split[0]);
//        System.out.println(split[1]);

    }

    @Test
    public void test12() throws NoSuchFieldException, IllegalAccessException {
        int oldCapacity = 16;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
//        String s1 = "helloworld";
//        Field valueArray = String.class.getDeclaredField("value");
//        valueArray.setAccessible(true);
//        char[] array = (char[]) valueArray.get(s1);
//        array[0] = 'e';
//        System.out.println(s1);
    }

    @Test
    public void test11() {
        String s1 = "helloworld";
        String s2 = "helloworld";
        String s3 = "hello" + "world";
        String s4 = "hello";
        String s5 = "world";
        String s6 = s4 + s5;

        System.out.println(s1 == s2); // true
        System.out.println(s2 == s3); // true
        System.out.println(s3 == s6); // false
    }

    @Test
    public void test10() {
        String s1 = new String("qwe");
        String s2 = "qwe";
        String s3 = "qwe";
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);
    }

    @Test
    public void test9() {
        String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        int i = Integer.parseInt(integerCacheHighPropValue);
        System.out.println(i);
    }


    /**
     * 测试
     */
    @Test
    public void test8() {
        ForkJoinPool pool = new ForkJoinPool(5);
        pool.submit(() -> System.out.println("ssss"));
        List<String> list = new ArrayList<>();
        list.add("1123");
        list.add("1123123");
        list.add("1123445");
        list.add("1123876");
        list.add("1123876");
        List<String> collect = list.parallelStream().map(s -> {
            System.out.println(Thread.currentThread().getName());
            return s + "df";
        }).collect(Collectors.toList());

        System.out.println("================================");
        pool.submit(() -> list.parallelStream().forEach(s -> {
            System.out.println(Thread.currentThread().getName());
        })).join();
    }

    /**
     * 测试数字大写
     */
    @Test
    public void test7() {
        int x = 0x7fffffff; // 21
        // 4
        System.out.println(x);
        String pp = System.getProperty
                ("java.util.concurrent.ForkJoinPool.common.parallelism");
        String fp = System.getProperty
                ("java.util.concurrent.ForkJoinPool.common.threadFactory");
        String hp = System.getProperty
                ("java.util.concurrent.ForkJoinPool.common.exceptionHandler");

        System.out.println(pp);
        System.out.println(fp);
        System.out.println(hp);

    }

    @Test
    public void test6() {
        int end = 4;
        int start = 1;
        int mid = (end - start) / 2 + start;
        System.out.println(mid);

    }

    @Test
    public void test5() {
//        int z = 'z';
//        char c = '0' - 1;
//        System.out.println(c);
//        System.out.println(z);
        String str = "fdsaf";
        System.out.println(str.indexOf("d"));

    }

    @Test
    public void test4() {
//        Integer i1 = new Integer(20);
//        Integer i2 = new Integer(20);
//        System.out.println(i1 == i2); // false
//
//        Integer i3 = new Integer(200);
//        Integer i4 = new Integer(200);
//        System.out.println(i3 == i4); // false

        // -128 - 127 都是用的缓存里面的值
        Integer i5 = 30;
        Integer i6 = 30;
        System.out.println(i5 == i6); // true
        Integer i7 = 300;
        Integer i8 = 300;
        System.out.println(i7 == i8); // false
    }


    @Test
    public void test3() {
        Thread th = Thread.currentThread();
        int i = 0;
        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1);
                    th.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        while (true) {
            if (th.isInterrupted()) {
                break;
            }
            System.out.println(++i);
            // 省略业务代码无数
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2() {
//        double log = Math.log(4);
//        System.out.println(log);
//        Double d = 10d;
//
//        d = d / 3 ;
//        int i = d.intValue();
//        System.out.println(i);

        String str = "20";

        double v = Double.parseDouble(str);
        int i = Double.valueOf(str).intValue();

    }

    @Test
    public void test() {
//        Integer i1 = 3;
//        Integer i2 = 2;
//        // 小于 -1
//        // 等于 0
//        // 大于 1
//        System.out.println(i1.compareTo(i2));

        double d1 = 1;
        double d2 = 3;
        System.out.println(d1 / d2);

    }

}
