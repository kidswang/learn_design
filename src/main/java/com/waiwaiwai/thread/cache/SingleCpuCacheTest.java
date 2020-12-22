package com.waiwaiwai.thread.cache;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/15 13:45
 * @Description: 测试单核Cpu对同一个数进行操作
 */
public class SingleCpuCacheTest {

    private long id = 10;

    @Test
    public void test() throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.execute(() -> {
//            for (int i = 0; i <= 100000000; i++) {
//                id = id + 1;
//            }
//        });
//
//        executorService.execute(() -> {
//            for (int i = 0; i <= 100000000; i++) {
//                id = id + 1;
//            }
//        });
//        executorService.execute(() -> {
//            id = id + 10;
//            for (int i = 0; i <= 30000000; i++) {
//                id = id + 1;
//                System.out.println(id);
//            }
//        }); // 60028   60032

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) {
                id += 1;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) {
                id += 1;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(id);

    }


}
