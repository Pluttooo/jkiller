package com.jkiller.killer.basics.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    // 固定速率执行
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRate() {
        logger.info("current thread : {}", Thread.currentThread().getName());
        logger.info("fixed rate task: the time now is {}", simpleDateFormat.format(new Date()));

    }

    // 固定延迟执行
    @Scheduled(fixedDelay = 2000)
    public void reportCurrentTimeWithFixedDelay() throws InterruptedException{
        Thread.sleep(3000);
        logger.info("current thread : {}", Thread.currentThread().getName());
        logger.info("fixed delay task: the time now is {}", simpleDateFormat.format(new Date()));
    }
}
