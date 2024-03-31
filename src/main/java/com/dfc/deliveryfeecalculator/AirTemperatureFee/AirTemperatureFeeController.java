package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/AirTemperatureFee")
public class AirTemperatureFeeController {

    private final AirTemperatureFeeService airTemperatureFeeService;

    @Autowired
    public AirTemperatureFeeController(AirTemperatureFeeService airTemperatureFeeService) {
        this.airTemperatureFeeService = airTemperatureFeeService;
    }

    @GetMapping("/getAllAirTemperatureFee")
    public ResponseEntity<AirTemperatureFee> getAirTemperatureFee() {
        return airTemperatureFeeService.getAirTemperatureFee();
    }

    @PostMapping("/updateAirTemperatureFee")
    public ResponseEntity<AirTemperatureFee> updateAirTemperatureFee(@RequestBody AirTemperatureFee newAirTemperatureFee) {
        return airTemperatureFeeService.updateAirTemperatureFee(newAirTemperatureFee);
    }
}
