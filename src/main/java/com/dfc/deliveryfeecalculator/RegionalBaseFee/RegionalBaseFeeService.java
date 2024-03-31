package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<RegionalBaseFee> updateRegionalBaseFee(String cityName, RegionalBaseFee newRegionalBaseFee) {
        try {
            Optional<RegionalBaseFee> existingBaseFee = regionalBaseFeeRepository.findById(cityName);
            if (existingBaseFee.isPresent()) {
                RegionalBaseFee baseFee = existingBaseFee.get();
                baseFee.setCarFee(newRegionalBaseFee.getCarFee());
                baseFee.setScooterFee(newRegionalBaseFee.getScooterFee());
                baseFee.setBikeFee(newRegionalBaseFee.getBikeFee());
                regionalBaseFeeRepository.save(baseFee);
                return new ResponseEntity<>(baseFee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
