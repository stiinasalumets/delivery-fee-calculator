package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WindSpeedFeeService {
    private final WindSpeedFeeRepository windSpeedFeeRepository;

    @Autowired
    public WindSpeedFeeService(WindSpeedFeeRepository windSpeedFeeRepository) {
        this.windSpeedFeeRepository = windSpeedFeeRepository;
    }

    public ResponseEntity<List<WindSpeedFee>> getAllWindSpeedFee(){
        try {
            List<WindSpeedFee> windSpeedFeeList = new ArrayList<>(windSpeedFeeRepository
                    .findAll());
            if (windSpeedFeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(windSpeedFeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
