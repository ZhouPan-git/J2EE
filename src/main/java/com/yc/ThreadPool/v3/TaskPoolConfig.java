package com.yc.ThreadPool.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author zp
 * @Date 2023/7/25 19:17
 * @PackageName:com.yc.ThreadPool.v3
 * @ClassName: TaskPoolConfig
 * @Description:
 * @Version 1.0
 */
@Component
class TimePrinter {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void printCurrentTime() {
        for (int i = 0; i < 5; i++) {
            threadPoolTaskExecutor.execute(() -> {
                System.out.println(new Date());
            });
        }
    }

}

@Configuration
@ComponentScan
public class TaskPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        return executor;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TaskPoolConfig.class);
        TimePrinter timePrinter = ac.getBean(TimePrinter.class);
        timePrinter.printCurrentTime();
    }
}