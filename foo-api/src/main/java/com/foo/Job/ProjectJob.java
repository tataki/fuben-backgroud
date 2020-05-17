package com.foo.Job;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProjectJob {

    @Scheduled(cron = "0/3 * * * * ? ")
    public void autoCloseOrder(){
        System.out.println("执行定时任务");
    }
}
