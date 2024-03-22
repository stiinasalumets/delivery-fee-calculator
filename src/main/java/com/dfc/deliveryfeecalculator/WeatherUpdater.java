package com.dfc.deliveryfeecalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class WeatherUpdater {

    Logger logger = LoggerFactory.getLogger(DeliveryFeeCalculatorApplication.class);

    @Scheduled(cron = "0 15 * * * *")
    public void computePrice() throws InterruptedException {
        logger.info("Current date" + new Date());
    }
}
