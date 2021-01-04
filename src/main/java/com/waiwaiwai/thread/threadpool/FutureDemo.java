package com.waiwaiwai.thread.threadpool;

import com.waiwaiwai.mydesign.openandclose.common.Person;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/28 13:48
 * @Description:
 */
public class FutureDemo {

    /*
      三个 sumit方法
        // 提交Runnable任务
        Future<?>
          submit(Runnable task); // 参数是Runnable接口，这个接口的run()方法没有返回值.
        // 提交Callable任务
        <T> Future<T>
          submit(Callable<T> task); // 参数是Callable接口,这个接口的run方法可以通过get()方法获取任务的执行结果.
        // 提交Runnable任务及结果引用
        <T> Future<T>
          submit(Runnable task, T result); //
     */
    // 可以通过这个方法实现主线程与子线程之间的数据共享
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Person person = new Person();
        person.setName("张三");

        Future<Person> future = executorService.submit(new Task(person), person);

        Person person1 = future.get();

        System.out.println(person == person1);
        System.out.println(person1.getName());
        System.out.println(person1.getGradeId());
    }

    public static class Task implements Runnable {
        Person p;

        public Task(Person p) {
            this.p = p;
        }

        @Override
        public void run() {
            String name = p.getName();
            p.setGradeId("123");
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        test();
    }
}
