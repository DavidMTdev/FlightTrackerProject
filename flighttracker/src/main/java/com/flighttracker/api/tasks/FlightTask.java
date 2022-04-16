package com.flighttracker.api.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FlightTask {
    
    @Scheduled(cron = "* * * * * *")
    public void launchJob() {
        System.out.println("ok");
    }
}
