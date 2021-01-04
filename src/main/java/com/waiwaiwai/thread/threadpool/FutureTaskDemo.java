package com.waiwaiwai.thread.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/28 14:10
 * @Description: futureTask类demo
 */
public class FutureTaskDemo {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> 1 + 2);

        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(task);

        Integer result = task.get();
        System.out.println(result);

    }




}
