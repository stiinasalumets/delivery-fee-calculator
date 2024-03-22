package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RegionalBaseFeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(RegionalBaseFeeRepository repository) {
        return args -> {
            RegionalBaseFee regionalBaseFeeTallinn = new RegionalBaseFee("Tallinn-Harku", 4F, 3.5F, 3F);
            RegionalBaseFee regionalBaseFeeTartu = new RegionalBaseFee("Tartu-Tõravere", 3.5F, 3F, 2.5F);
            RegionalBaseFee regionalBaseFeeParnu = new RegionalBaseFee("Tallinn-Harku", 3F, 2.5F, 2F);

            repository.saveAll(List.of(regionalBaseFeeTallinn, regionalBaseFeeTartu, regionalBaseFeeParnu));
        };
    }
}
