package com.dfc.deliveryfeecalculator.DeliveryFeeCalculator;

import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFee;
import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/DeliveryFeeCalculator")
public class DeliveryFeeCalculatorController {

    private final DeliveryFeeCalculatorService deliveryFeeCalculatorService;

    @Autowired
    public DeliveryFeeCalculatorController(DeliveryFeeCalculatorService deliveryFeeCalculatorService) {
        this.deliveryFeeCalculatorService = deliveryFeeCalculatorService;
    }

    @GetMapping("/getDeliveryFee")
    public ResponseEntity<Float> getDeliveryFee(@RequestParam String city, @RequestParam String vehicle){
        return deliveryFeeCalculatorService.getDeliveryFee(city, vehicle);
    }

}
