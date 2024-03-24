package com.dfc.deliveryfeecalculator.WindSpeedFee;

import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WindSpeedFeeService {
    private final WindSpeedFeeRepository windSpeedFeeRepository;

    @Autowired
    public WindSpeedFeeService(WindSpeedFeeRepository windSpeedFeeRepository) {
        this.windSpeedFeeRepository = windSpeedFeeRepository;
    }

    public ResponseEntity<WindSpeedFee> getWindSpeedFee(){
        try {
            Optional<WindSpeedFee> windSpeedFee = windSpeedFeeRepository.findById(1L);
            return windSpeedFee.map(windFee -> new ResponseEntity<>(windFee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
