package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/WeatherPhenomenonFee")
public class WeatherPhenomenonFeeController {

    private final WeatherPhenomenonFeeService weatherPhenomenonFeeService;

    @Autowired
    public WeatherPhenomenonFeeController(WeatherPhenomenonFeeService weatherPhenomenonFeeService) {
        this.weatherPhenomenonFeeService = weatherPhenomenonFeeService;
    }

    @GetMapping("/getWeatherPhenomenonFee")
    public ResponseEntity<WeatherPhenomenonFee> getWeatherPhenomenonFee(){
        return weatherPhenomenonFeeService.getWeatherPhenomenonFee();
    }
}
