package com.example.client.annotation;

import com.example.client.config.ThreadPoolConfig;
import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Async(ThreadPoolConfig.FAST_THREAD_POOL)
public @interface FastAsyncTask {
}
