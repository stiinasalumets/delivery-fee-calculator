package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/AirTemperatureFee")
public class AirTemperatureFeeController {

    private final AirTemperatureFeeService airTemperatureFeeService;

    @Autowired
    public AirTemperatureFeeController(AirTemperatureFeeService airTemperatureFeeService) {
        this.airTemperatureFeeService = airTemperatureFeeService;
    }

    @GetMapping("/getAllAirTemperatureFee")
    public ResponseEntity<List<AirTemperatureFee>> getAllAirTemperatureFee(){
        return airTemperatureFeeService.getAllAirTemperatureFee();
    }


}
