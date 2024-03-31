package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirTemperatureFeeService {
    private final AirTemperatureFeeRepository airTemperatureFeeRepository;

    @Autowired
    public AirTemperatureFeeService(AirTemperatureFeeRepository airTemperatureFeeRepository) {
        this.airTemperatureFeeRepository = airTemperatureFeeRepository;
    }

    public ResponseEntity<AirTemperatureFee> getAirTemperatureFee() {
        try {
            Optional<AirTemperatureFee> airTemperatureFee = airTemperatureFeeRepository.findById(1L);
            return airTemperatureFee.map(temperatureFee -> new ResponseEntity<>(temperatureFee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
