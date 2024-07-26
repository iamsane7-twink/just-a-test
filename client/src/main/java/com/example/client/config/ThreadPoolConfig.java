package com.example.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {
    public static final String FAST_THREAD_POOL = "fastThreadPool";

    @Value("${corePoolSize}")
    private int corePoolSize;
    @Value("${maxPoolSize}")
    private int maxPoolSize;
    @Value("${queueCapacity}")
    private int queueCapacity;

    @Bean(FAST_THREAD_POOL)
    public ThreadPoolTaskExecutor fastThreadPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix("fast-tasks-");
        taskExecutor.setRejectedExecutionHandler((runnable, executor) -> log.warn("Couldn't get thread, queue is full"));
        taskExecutor.initialize();

        return taskExecutor;
    }
}
