package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/WeatherPhenomenonFee")
public class WeatherPhenomenonFeeController {

    private final WeatherPhenomenonFeeService weatherPhenomenonFeeService;

    @Autowired
    public WeatherPhenomenonFeeController(WeatherPhenomenonFeeService weatherPhenomenonFeeService) {
        this.weatherPhenomenonFeeService = weatherPhenomenonFeeService;
    }

    @GetMapping("/getWeatherPhenomenonFee")
    public ResponseEntity<WeatherPhenomenonFee> getWeatherPhenomenonFee() {
        return weatherPhenomenonFeeService.getWeatherPhenomenonFee();
    }

    @PostMapping("/updateWeatherPhenomenonFee")
    public ResponseEntity<WeatherPhenomenonFee> updateWeatherPhenomenonFee(@RequestBody WeatherPhenomenonFee newWeatherPhenomenonFee) {
        return weatherPhenomenonFeeService.updateWeatherPhenomenonFee(newWeatherPhenomenonFee);
    }
}
