package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionalBaseFeeService {
    private final RegionalBaseFeeRepository regionalBaseFeeRepository;

    @Autowired
    public RegionalBaseFeeService(RegionalBaseFeeRepository regionalBaseFeeRepository) {
        this.regionalBaseFeeRepository = regionalBaseFeeRepository;
    }

    public ResponseEntity<List<RegionalBaseFee>> getAllRegionalBaseFee(){
        try {
            List<RegionalBaseFee> regionalBaseFeeList = new ArrayList<>(regionalBaseFeeRepository
                    .findAll());
            if (regionalBaseFeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(regionalBaseFeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
