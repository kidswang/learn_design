package com.waiwaiwai.thread.threadpool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/28 15:00
 * @Description: 烧水泡茶案例
 */
public class BoilingWaterTeaDemo {


    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new Task2());
        FutureTask<String> ft1 = new FutureTask<>(new Task1(ft2));

        // 线程T1执行任务ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程T2执行任务ft2
        Thread T2 = new Thread(ft2);
        T2.start();

        System.out.println(ft1.get());
    }


    public static class Task1 implements Callable<String> {
        FutureTask<String> ft2;

        public Task1(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @SneakyThrows
        @Override
        public String call() {
            System.out.println("t1 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("t1 烧开水...");
            TimeUnit.SECONDS.sleep(15);

            // 等带ft2 完成，从ft2中拿数据
            String tea = ft2.get();
            System.out.println("t1 拿到茶叶 " + tea);

            System.out.println("t1 泡茶...");
            TimeUnit.SECONDS.sleep(1);

            return "上茶 " + tea;
        }
    }

    public static class Task2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }

}
