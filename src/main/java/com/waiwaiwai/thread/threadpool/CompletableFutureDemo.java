package com.waiwaiwai.thread.threadpool;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/29 13:59
 * @Description: CompletableFuture 类Demo
 * <p>
 * CompletableFuture 有四个方法
 * <p>
 * //使用默认线程池
 * static CompletableFuture<Void> runAsync(Runnable runnable)
 * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * //可以指定线程池
 * static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 */
public class CompletableFutureDemo {


    /*
        异常处理
        CompletionStage exceptionally(fn);
        CompletionStage<R> whenComplete(consumer); 不支持返回结果
        CompletionStage<R> whenCompleteAsync(consumer);
        CompletionStage<R> handle(fn);  支持返回结果
        CompletionStage<R> handleAsync(fn);
     */

    /**
     * 测试 exceptionally() 方法
     */
    @Test
    public void exceptionallyTest() {
        CompletableFuture<String> ft1 = CompletableFuture.supplyAsync(() -> "hello word").thenApply(s -> {
            int j = 5 / 0;
            return "sss" + s;
        }).exceptionally(Throwable::toString).whenComplete((s, throwable) -> System.out.println(s));
        System.out.println(ft1.join());

    }


    /*
        描述or汇聚关系
        CompletionStage applyToEither(other, fn);
        CompletionStage applyToEitherAsync(other, fn);
        CompletionStage acceptEither(other, consumer);
        CompletionStage acceptEitherAsync(other, consumer);
        CompletionStage runAfterEither(other, action);
        CompletionStage runAfterEitherAsync(other, action);
     */

    /**
     * 测试 applyToEither() 方法
     */
    @Test
    public void applyToEitherTest() {

        CompletableFuture<String> ft1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "龙井";
        });
        CompletableFuture<String> ft2 = CompletableFuture.supplyAsync(() -> "观音");
        // 两个线程谁先来就用谁
        CompletableFuture<String> ft3 = ft1.applyToEither(ft2, s -> s);
        System.out.println(ft3.join());

    }


    /*
        描述and组合关系
        CompletionStage<R> thenCombine(other, fn);
        CompletionStage<R> thenCombineAsync(other, fn);
        CompletionStage<Void> thenAcceptBoth(other, consumer);
        CompletionStage<Void> thenAcceptBothAsync(other, consumer);
        CompletionStage<Void> runAfterBoth(other, action);
        CompletionStage<Void> runAfterBothAsync(other, action);
     */


    /*
        描述串行关系
        CompletionStage<R> thenApply(fn);
        CompletionStage<R> thenApplyAsync(fn);
        CompletionStage<Void> thenAccept(consumer);
        CompletionStage<Void> thenAcceptAsync(consumer);
        CompletionStage<Void> thenRun(action);
        CompletionStage<Void> thenRunAsync(action);
        CompletionStage<R> thenCompose(fn);
        CompletionStage<R> thenComposeAsync(fn);
     */

    /**
     * 测试 thenApply() 方法
     */
    @Test
    public void thenApplyTest() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello world ")
                .thenApply(s -> s + "qq")
                .thenApply(String::toUpperCase);

        String join = stringCompletableFuture.join();
        System.out.println(join);

    }

    /**
     * 测试 CompletableFuture 异步工具类
     */
    @Test
    public void test() {
        CompletableFuture<Void> ft1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("洗水壶");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("烧开水");
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> ft2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("洗茶壶");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("洗茶杯");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("拿茶叶");
                TimeUnit.SECONDS.sleep(1);
                return "龙井";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "龙井";
            }
        });

        CompletableFuture<String> ft3 = ft1.thenCombine(ft2, (__, tf) -> {
//            String tea = "";
//            tea = ft2.get();
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");

            return "上茶:" + tf;

        });
        System.out.println(ft3.join());

    }

}
