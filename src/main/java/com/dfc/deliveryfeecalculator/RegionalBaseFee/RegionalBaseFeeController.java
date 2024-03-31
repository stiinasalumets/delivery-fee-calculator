package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/RegionalBaseFee")
public class RegionalBaseFeeController {

    private final RegionalBaseFeeService regionalBaseFeeService;

    @Autowired
    public RegionalBaseFeeController(RegionalBaseFeeService regionalBaseFeeService) {
        this.regionalBaseFeeService = regionalBaseFeeService;
    }

    @GetMapping("/getAllRegionalBaseFee/{cityName}")
    public ResponseEntity<RegionalBaseFee> getRegionalBaseFee(@PathVariable String cityName) {
        return regionalBaseFeeService.getRegionalBaseFee(cityName);
    }

    @PostMapping("/updateRegionalBaseFee/{cityName}")
    public ResponseEntity<RegionalBaseFee> updateRegionalBaseFee(@PathVariable String cityName, @RequestBody RegionalBaseFee newRegionalBaseFee) {
        return regionalBaseFeeService.updateRegionalBaseFee(cityName, newRegionalBaseFee);
    }
}
