package com.parkingLotSystem.Parking.Executor;

import jdk.jfr.Enabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class Executor {

    @Scheduled(cron = "0 0/1 * * * ?")
    //@Scheduler
    public void forceUnpark(){

        log.info("This is Unpark Method");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();


    }

}
