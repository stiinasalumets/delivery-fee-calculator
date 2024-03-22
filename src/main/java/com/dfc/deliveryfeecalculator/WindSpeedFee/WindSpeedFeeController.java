package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/WindSpeedFee")
public class WindSpeedFeeController {

    private final WindSpeedFeeService windSpeedFeeService;

    @Autowired
    public WindSpeedFeeController(WindSpeedFeeService windSpeedFeeService) {
        this.windSpeedFeeService = windSpeedFeeService;
    }

    @GetMapping("/getAllWindSpeedFee")
    public ResponseEntity<List<WindSpeedFee>> getAllWindSpeedFee(){
        return windSpeedFeeService.getAllWindSpeedFee();
    }


}
