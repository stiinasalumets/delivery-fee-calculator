package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RegionalBaseFeeConfig implements ApplicationRunner {

    private final RegionalBaseFeeRepository regionalBaseFeeRepository;

    @Autowired
    public RegionalBaseFeeConfig(RegionalBaseFeeRepository regionalBaseFeeRepository) {
        this.regionalBaseFeeRepository = regionalBaseFeeRepository;
    }

    public void run(ApplicationArguments args) {
        regionalBaseFeeRepository.save(new RegionalBaseFee("Tallinn", 4F, 3.5F, 3F));
        regionalBaseFeeRepository.save(new RegionalBaseFee("Tartu", 3.5F, 3F, 2.5F));
        regionalBaseFeeRepository.save(new RegionalBaseFee("Parnu", 3F, 2.5F, 2F));
    }
}
