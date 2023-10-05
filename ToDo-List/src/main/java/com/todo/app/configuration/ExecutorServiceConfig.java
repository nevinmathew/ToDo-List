package com.todo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class for defining a custom ExecutorService.
 */
@Configuration
public class ExecutorServiceConfig {

    /**
     * Creates a custom ThreadPoolTaskExecutor bean for asynchronous task execution.
     *
     * @return The configured ThreadPoolTaskExecutor.
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CustomExecutor-");
        executor.initialize();
        return executor;
    }
}