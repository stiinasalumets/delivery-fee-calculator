package com.dfc.deliveryfeecalculator.WeatherInfo;

import com.dfc.deliveryfeecalculator.WeatherUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class WeatherInfoConfig implements ApplicationRunner {

    private final WeatherUpdater weatherUpdater;

    @Autowired
    public WeatherInfoConfig(WeatherUpdater weatherUpdater) {
        this.weatherUpdater = weatherUpdater;
    }

    public void run(ApplicationArguments args) {
        weatherUpdater.addWeatherInfo();
    }
}
