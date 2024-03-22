package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WeatherInfoConfig {

    @Bean
    CommandLineRunner commandLineRunner(WeatherInfoRepository repository) {
        return args -> {
            WeatherInfo weatherInfoTallinn = new WeatherInfo("Tallinn", 2, 2.1F, 1.1F, "hail");
            WeatherInfo weatherInfoTartu = new WeatherInfo("Tartu", 3, 2.5F, 2.1F, "hail");
            WeatherInfo weatherInfoParnu = new WeatherInfo("Parnu", 4, 5.5F, 2.3F, "hail");
            repository.saveAll(List.of(weatherInfoTallinn, weatherInfoTartu, weatherInfoParnu));
        };
    }
}
