package com.waiwaiwai.thread.threadpool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 15:44
 * @Description:
 */
public class CompletionServiceDemo {

    /*

    对于简单的并行任务，你可以通过“线程池 +Future”的方案来解决；
    如果任务之间有聚合关系，无论是 AND 聚合还是 OR 聚合，都可以通过 CompletableFuture 来解决；
    而批量的并行任务，则可以通过 CompletionService 来解决。
     */


    // 实现高可用的获取数据方式
    // 只要有一个线程返回值了就用这个值，然后取消所有的任务

    @Test
    public void test3() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        BlockingQueue<Future<String>> queue = new LinkedBlockingQueue<>(3);

        ExecutorCompletionService<String> cs = new ExecutorCompletionService<>(executorService, queue);

        List<Future<String>> futures = new ArrayList<>(3);

        Future<String> f1 = cs.submit(() -> "fd");

        Future<String> f2 = cs.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "zd";
        });

        Future<String> f3 = cs.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "ad";
        });

        futures.add(f1);
        futures.add(f2);
        futures.add(f3);

        try {
            for (int i = 0; i < 3; i++) {
                String s = cs.poll(3, TimeUnit.SECONDS).get();
                if (s != null) {
                    System.out.println(s);
                    break;
                }
            }
        } finally {
            for (Future<String> future : futures) {
                future.cancel(true);
            }
        }
//        for (int i = 0; i < 3; i++) {
//            String s = cs.take().get();
//            if (s != null) {
//                System.out.println(s);
//                break;
//            }
//        }

    }


    /*

        Future<V> submit(Callable<V> task); // 执行任务
        Future<V> submit(Runnable task, V result); // 这个方法用于父子线程之间共享数据. 子线程可以获取、设置 result 的值
        Future<V> take() throws InterruptedException;  // 从阻塞队列中获取并移除一个元素, 阻塞队列如果是空的就阻塞
        Future<V> poll(); // 从阻塞队列中获取并移除一个元素, 如果阻塞队列是空的就返回 null
        Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
        //支持以超时的形式从阻塞队列中获取并移除一个元素,如果等待了 timeout 时间阻塞队列还是空的就返回 null
     */

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        BlockingQueue<Future<String>> queue = new LinkedBlockingQueue<>(3);
        ExecutorCompletionService<String> cs = new ExecutorCompletionService<>(executorService, queue);

        cs.submit(() -> "fd");

        cs.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "zd";
        });

        cs.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "ad";
        });

        for (int i = 0; i < 3; i++) {
            System.out.println(cs.poll(3, TimeUnit.SECONDS).get());
        }

    }


    // improve
    // 使用阻塞队列 + 可返回数据线程 获取数据
    @Test
    public void test1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        Future<String> f1 = executorService.submit(() -> "fd");
        Future<String> f2 = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "zd";
        });
        Future<String> f3 = executorService.submit(() -> "ad");

        executorService.execute(() -> {
            try {
                queue.put(f1.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                queue.put(f2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                queue.put(f3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.take());
        }

    }


    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> f1 = executorService.submit(() -> "fd");
        Future<String> f2 = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "zd";
        });
        Future<String> f3 = executorService.submit(() -> "ad");

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());

    }


//    @Override
//    public Object call() throws Exception {
//
//        return null;
//    }
}
