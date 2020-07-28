package com.santosh.springredis.bean;

import com.santosh.springredis.cache.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ScheduleTask {
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        log.info(
                "Fixed rate task with one second initial delay - " + now);

        ExecutorService executor = Executors.newFixedThreadPool(1000);

        int count = 10000;
        for (int i = 1; i <= count; i++) {
            //String id = String.valueOf(i);
            String id = "1";
            executor.execute(new NamePrintingTask(userTokenRepository, id, "name" + i));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
