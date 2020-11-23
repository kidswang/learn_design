package com.waiwaiwai.thread.simp;

import com.waiwaiwai.thread.simp.util.DateFormatUtil;
import org.junit.Test;

import java.util.Date;

public class SimpleDateTest {

        @Test
        public void test() {
//            ExecutorService pool = Executors.newFixedThreadPool(10);

            Runnable t1 = new MyThread();
            Runnable t2 = new MyThread();
            Runnable t3 = new MyThread();
            Runnable t4 = new MyThread();
            Runnable t5 = new MyThread();
            Runnable t6 = new MyThread();
            Runnable t7 = new MyThread();
            Runnable t8 = new MyThread();
            Runnable t9 = new MyThread();
            Runnable t10 = new MyThread();


            t1.run();
            t2.run();
            t3.run();
            t4.run();
            t5.run();
            t6.run();
            t7.run();
            t8.run();
            t9.run();
            t10.run();

//            pool.execute(t1);
//            pool.execute(t2);
//            pool.execute(t3);
//            pool.execute(t4);
//            pool.execute(t5);
//            pool.execute(t6);
//            pool.execute(t7);
//            pool.execute(t8);
//            pool.execute(t9);
//            pool.execute(t10);
//            pool.shutdown();
        }

}

class MyThread extends Thread {

    @Override
    public void run() {
        Date date = new Date();
        String s = DateFormatUtil.dateFormat(date);
        System.out.println(s);
    }
}
