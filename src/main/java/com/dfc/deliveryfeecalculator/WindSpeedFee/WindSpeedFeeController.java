package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/WindSpeedFee")
public class WindSpeedFeeController {

    private final WindSpeedFeeService windSpeedFeeService;

    @Autowired
    public WindSpeedFeeController(WindSpeedFeeService windSpeedFeeService) {
        this.windSpeedFeeService = windSpeedFeeService;
    }

    @GetMapping("/getAllWindSpeedFee")
    public ResponseEntity<WindSpeedFee> getWindSpeedFee() {
        return windSpeedFeeService.getWindSpeedFee();
    }

    @PostMapping("/updateWindSpeedFee")
    public ResponseEntity<WindSpeedFee> updateWindSpeedFee(@RequestBody WindSpeedFee newWindSpeedFee) {
        return windSpeedFeeService.updateWindSpeedFee(newWindSpeedFee);
    }
}
