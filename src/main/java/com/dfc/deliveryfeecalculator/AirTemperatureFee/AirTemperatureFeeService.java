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

    public ResponseEntity<AirTemperatureFee> updateAirTemperatureFee(AirTemperatureFee newAirTemperatureFee) {
        try {
            Optional<AirTemperatureFee> existingAirTemperatureFee = airTemperatureFeeRepository.findById(1L);
            if (existingAirTemperatureFee.isPresent()) {
                AirTemperatureFee airTemperatureFee = existingAirTemperatureFee.get();
                airTemperatureFee.setLowerTemperature(newAirTemperatureFee.getLowerTemperature());
                airTemperatureFee.setHigherTemperature(newAirTemperatureFee.getHigherTemperature());
                airTemperatureFee.setLowestRangeFee(newAirTemperatureFee.getLowestRangeFee());
                airTemperatureFee.setMiddleRangeFee(newAirTemperatureFee.getMiddleRangeFee());
                airTemperatureFee.setHighestRangeFee(newAirTemperatureFee.getHighestRangeFee());
                airTemperatureFeeRepository.save(airTemperatureFee);
                return new ResponseEntity<>(airTemperatureFee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
