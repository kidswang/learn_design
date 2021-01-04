package com.waiwaiwai.thread.forkjoin;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/4 9:39
 * @Description: 分治思想  fork/join
 */
public class ForkJoinDemo {

    // Fork 是对任务的分解
    // Join 是对结果的合并

    /**
     * 模拟
     */

    /**
     * 获取机器 cpu 核数
     */
    @Test
    public void test3() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    ForkJoinPool fjp = new ForkJoinPool(4);

    /**
     * 测试斐波那契数列
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        Fibonacci fibonacci = new Fibonacci(3);
//        Integer invoke = fjp.invoke(fibonacci);
//        System.out.println(invoke);
        ForkJoinTask<Integer> submit = fjp.submit(fibonacci);
        System.out.println(submit.get());
    }

    /**
     * fork 会异步的执行一个子任务
     * join 会阻塞当前线程来等待子任务的执行结果
     */
    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            // 分解子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n -2);
            f2.fork();
            // 任务计算
            // f2 是在这个线程里面计算的
            Integer compute = f2.compute();
            // 合并结果
            // f1 是在一个子任务中计算的 需要等待它的结果.
            // 所以 f2 是直接调用 compute 方法, 而 f1 需要调用 join 阻塞并等待任务的执行结果
            Integer join = f1.join();
            return compute + join;
        }
    }


    /**
     * 查看 RecursiveAction 和 RecursiveTask
     */
    public void test1() {

        RecursiveAction action = new RecursiveAction() {
            @Override
            protected void compute() {

            }
        };

        RecursiveTask<String> recursiveTask = new RecursiveTask<String>() {
            @Override
            protected String compute() {
                return null;
            }
        };

        Abstract1 abstract1 = new Abstract1() {
            @Override
            public void task() {
                System.out.println("task");
            }
        };
        abstract1.task1();
        abstract1.task();

        ForkJoinTask<String> task = new ForkJoinTask<String>() {
            @Override
            public String getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(String value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };

    }

    @Test
    public void teskVirtual() {
        Abstract1 abstract1 = new Abstract1(1) {
            @Override
            public void task() {
                System.out.println("task");
            }
        };
        abstract1.task1();
        abstract1.task();
        System.out.println( abstract1.flag);
    }

}
