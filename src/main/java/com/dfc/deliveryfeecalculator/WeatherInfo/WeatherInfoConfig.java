package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component
public class WeatherInfoConfig implements ApplicationRunner {

    private WeatherInfoRepository weatherInfoRepository;

    @Autowired
    public WeatherInfoConfig(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }

    public void run(ApplicationArguments args) {
        weatherInfoRepository.save(new WeatherInfo("Tallinn", 2, 2.1F, 1.1F, "hail"));
        weatherInfoRepository.save(new WeatherInfo("Tartu", 3, 2.5F, 2.1F, "hail"));
        weatherInfoRepository.save(new WeatherInfo("Parnu", 4, 5.5F, 2.3F, "hail"));
    }
}
