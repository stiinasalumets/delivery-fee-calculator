package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class WindSpeedFeeConfig implements ApplicationRunner {

    private final WindSpeedFeeRepository windSpeedFeeRepository;

    @Autowired
    public WindSpeedFeeConfig(WindSpeedFeeRepository windSpeedFeeRepository) {
        this.windSpeedFeeRepository = windSpeedFeeRepository;
    }

    public void run(ApplicationArguments args) {
        windSpeedFeeRepository.save(new WindSpeedFee(10F, 20F, 0.5F, 0F));
    }
}
