package com.waiwaiwai.thread.threadpool;


import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

//简化的线程池，仅用来说明工作原理
public class MyThreadPool {
    //利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;
    //保存内部工作线程
    List<WorkerThread> threads = new ArrayList<>();

    // 构造方法
    MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建工作线程
        for (int idx = 0; idx < poolSize; idx++) {
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);
        }
    }

    // 提交任务
    void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    // 工作线程负责消费任务，并执行任务
    class WorkerThread extends Thread {
        @SneakyThrows
        public void run() {
            //循环取任务并执行
            while (true) {
//                System.out.print(Thread.currentThread().getName());
                // 阻塞队列，如果队列是空的就阻塞在这里。
                Thread.sleep(1000);
                Runnable task = workQueue.take();
//                System.out.println("zhi da yin yi ci ");
                task.run();
            }
        }
    }


}

