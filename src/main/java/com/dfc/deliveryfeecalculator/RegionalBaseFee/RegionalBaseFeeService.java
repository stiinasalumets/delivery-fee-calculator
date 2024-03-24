package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionalBaseFeeService {
    private final RegionalBaseFeeRepository regionalBaseFeeRepository;

    @Autowired
    public RegionalBaseFeeService(RegionalBaseFeeRepository regionalBaseFeeRepository) {
        this.regionalBaseFeeRepository = regionalBaseFeeRepository;
    }

    public ResponseEntity<RegionalBaseFee> getRegionalBaseFee(String cityName) {
        try {
            Optional<RegionalBaseFee> regionalBaseFee = regionalBaseFeeRepository.findById(cityName);
            return regionalBaseFee.map(baseFee -> new ResponseEntity<>(baseFee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
