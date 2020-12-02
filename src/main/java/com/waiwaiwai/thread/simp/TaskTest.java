package com.waiwaiwai.thread.simp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@RequiredArgsConstructor
public class TaskTest {

    @Scheduled(cron = "*/10 * * * * ?")
    public void configureTasks() throws InterruptedException {
        System.out.println(new Date());
        Thread.sleep(11000);// millis
        System.out.println("线程名称" + Thread.currentThread().getName() + new Date());
    }

}
