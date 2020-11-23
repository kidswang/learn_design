package com.waiwaiwai.protocol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/23 13:30
 * @Description: 线程池
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, 100,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

}
