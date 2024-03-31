package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WindSpeedFeeService {
    private final WindSpeedFeeRepository windSpeedFeeRepository;

    @Autowired
    public WindSpeedFeeService(WindSpeedFeeRepository windSpeedFeeRepository) {
        this.windSpeedFeeRepository = windSpeedFeeRepository;
    }

    public ResponseEntity<WindSpeedFee> getWindSpeedFee() {
        try {
            Optional<WindSpeedFee> windSpeedFee = windSpeedFeeRepository.findById(1L);
            return windSpeedFee.map(windFee -> new ResponseEntity<>(windFee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WindSpeedFee> updateWindSpeedFee(WindSpeedFee newWindSpeedFee) {
        try {
            Optional<WindSpeedFee> existingWindSpeedFee = windSpeedFeeRepository.findById(1L);
            if (existingWindSpeedFee.isPresent()) {
                WindSpeedFee windSpeedFee = existingWindSpeedFee.get();
                windSpeedFee.setLowerWindSpeed(newWindSpeedFee.getLowerWindSpeed());
                windSpeedFee.setHigherWindSpeed(newWindSpeedFee.getHigherWindSpeed());
                windSpeedFee.setMiddleWindRange(newWindSpeedFee.getMiddleWindRange());
                windSpeedFee.setLowestWindRange(newWindSpeedFee.getLowestWindRange());
                windSpeedFeeRepository.save(windSpeedFee);
                return new ResponseEntity<>(windSpeedFee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
