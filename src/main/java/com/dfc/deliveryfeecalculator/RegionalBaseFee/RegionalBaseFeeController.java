package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/RegionalBaseFeeController")
public class RegionalBaseFeeController {

    private final RegionalBaseFeeService regionalBaseFeeService;

    @Autowired
    public RegionalBaseFeeController(RegionalBaseFeeService regionalBaseFeeService) {
        this.regionalBaseFeeService = regionalBaseFeeService;
    }

    @GetMapping("/getAllRegionalBaseFee")
    public ResponseEntity<List<RegionalBaseFee>> getAllRegionalBaseFee(){
        return regionalBaseFeeService.getAllRegionalBaseFee();
    }


}
