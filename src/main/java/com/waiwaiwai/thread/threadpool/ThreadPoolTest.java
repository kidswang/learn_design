package com.waiwaiwai.thread.threadpool;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public class ThreadPoolTest {
    private int i = 0;

    @Test
    public void test3() throws ExecutionException, InterruptedException {

        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        // 创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交FutureTask
        es.submit(futureTask);
        // 获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        ThreadFactory springThreadFactory = new CustomizableThreadFactory("springThread-pool-");

        RejectedExecutionHandler wo_man_le_man_le = (r, executor) -> System.out.println("wo man le man le");
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        WorkerThread workerThread = new WorkerThread();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 60, TimeUnit.SECONDS, workQueue, springThreadFactory, wo_man_le_man_le);
        ThreadPoolExecutor threadPoolExecutor = null;
//        try {
        threadPoolExecutor = new ThreadPoolExecutor(3, 10,
                60, TimeUnit.SECONDS, workQueue, springThreadFactory, abortPolicy);
        while (true) {
//                threadPoolExecutor.execute(workerThread);
            Future<?> submit = threadPoolExecutor.submit(workerThread);
            // Future.get()是阻塞式的,调用的时候,如果任务还没有执行完就会阻塞,直到任务执行完才会被唤醒
//                System.out.println(submit.get());
            // submit(Runnable task) 这个方法返回的future仅可以来断言任务已经结束了
            // submit(Callable task) 这个方法参数是一个Callable接口,这个方法是有返回值的,可以通过get方法获取任务的执行结果
            // submit(Runnable task, T result)
//                System.out.println(submit.isDone());
        }
//        } catch (Exception e) {
//            System.out.println("wo shi yi chang");
//        }

    }


    // 测试自定义的线程池
    @Test
    public void test1() throws InterruptedException {
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(1, workQueue);
        // 提交任务
        while (true) {
            pool.execute(() -> System.out.println("hello" + ++i));
            System.out.println(workQueue.size());

        }
//        Thread.sleep(10000);
    }

}

class WorkerThread extends Thread {
    @SneakyThrows
    public void run() {
        System.out.println(Thread.currentThread().getName());
        if (Thread.currentThread().getName().equals("main")) {
            Thread.sleep(100);
            System.out.println("wo shi main");
            return;
        }
        if (Thread.currentThread().getName().equals("springThread-pool-3")) {
            return;
        }
//        System.out.println(1 / 0);
        Thread.sleep(1000);
        System.out.println("zhi da yin yi ci ");
    }
}
