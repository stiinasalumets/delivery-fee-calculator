package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class WeatherPhenomenonFeeConfig implements ApplicationRunner {

    private final WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;

    @Autowired
    public WeatherPhenomenonFeeConfig(WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository) {
        this.weatherPhenomenonFeeRepository = weatherPhenomenonFeeRepository;
    }

    public void run(ApplicationArguments args) {
        weatherPhenomenonFeeRepository.save(new WeatherPhenomenonFee(1F, 1F, 0.5F));
    }
}
