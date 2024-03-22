package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherPhenomenonFeeService {
    private final WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;

    @Autowired
    public WeatherPhenomenonFeeService(WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository) {
        this.weatherPhenomenonFeeRepository = weatherPhenomenonFeeRepository;
    }

    public ResponseEntity<List<WeatherPhenomenonFee>> getAllWeatherPhenomenonFee(){
        try {
            List<WeatherPhenomenonFee> weatherPhenomenonFeeList = new ArrayList<>(weatherPhenomenonFeeRepository
                    .findAll());
            if (weatherPhenomenonFeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(weatherPhenomenonFeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
