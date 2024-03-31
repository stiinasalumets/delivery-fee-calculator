package com.dfc.deliveryfeecalculator.DeliveryFeeCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class DeliveryFeeCalculatorController {

    private final DeliveryFeeCalculatorService deliveryFeeCalculatorService;

    @Autowired
    public DeliveryFeeCalculatorController(DeliveryFeeCalculatorService deliveryFeeCalculatorService) {
        this.deliveryFeeCalculatorService = deliveryFeeCalculatorService;
    }

    @GetMapping("/getDeliveryFee")
    public ResponseEntity<Float> getDeliveryFee(@RequestParam String city, @RequestParam String vehicle, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        return deliveryFeeCalculatorService.getDeliveryFee(city, vehicle, date);
    }

}
