package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AirTemperatureFeeConfig implements ApplicationRunner {

    private final AirTemperatureFeeRepository airTemperatureFeeRepository;

    @Autowired
    public AirTemperatureFeeConfig(AirTemperatureFeeRepository airTemperatureFeeRepository) {
        this.airTemperatureFeeRepository = airTemperatureFeeRepository;
    }

    public void run(ApplicationArguments args) {
        airTemperatureFeeRepository.save(new AirTemperatureFee(-10F, 0F, 1F, 0.5F, 0F));
    }
}
