package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirTemperatureFeeService {
    private final AirTemperatureFeeRepository airTemperatureFeeRepository;

    @Autowired
    public AirTemperatureFeeService(AirTemperatureFeeRepository airTemperatureFeeRepository) {
        this.airTemperatureFeeRepository = airTemperatureFeeRepository;
    }

    public ResponseEntity<List<AirTemperatureFee>> getAllAirTemperatureFee(){
        try {
            List<AirTemperatureFee> airTemperatureFeeList = new ArrayList<>(airTemperatureFeeRepository
                    .findAll());
            if (airTemperatureFeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(airTemperatureFeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
