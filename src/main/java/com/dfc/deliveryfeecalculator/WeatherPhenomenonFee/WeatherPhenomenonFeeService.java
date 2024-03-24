package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherPhenomenonFeeService {
    private final WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;

    @Autowired
    public WeatherPhenomenonFeeService(WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository) {
        this.weatherPhenomenonFeeRepository = weatherPhenomenonFeeRepository;
    }

    public ResponseEntity<WeatherPhenomenonFee> getWeatherPhenomenonFee() {
        try {
            Optional<WeatherPhenomenonFee> weatherPhenomenonFee = weatherPhenomenonFeeRepository.findById(1L);
            return weatherPhenomenonFee.map(phenomenonFee -> new ResponseEntity<>(phenomenonFee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
