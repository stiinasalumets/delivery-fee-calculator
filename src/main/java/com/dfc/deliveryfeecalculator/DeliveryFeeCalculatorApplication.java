package com.dfc.deliveryfeecalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
public class DeliveryFeeCalculatorApplication {

    Logger logger = LoggerFactory.getLogger(DeliveryFeeCalculatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DeliveryFeeCalculatorApplication.class, args);
    }
}
