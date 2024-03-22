package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name="RegionalBaseFee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AirTemperatureFee {

    public AirTemperatureFee(Float lowerTemperature, Float higherTemperature, Float lowestRangeFee, Float middleRangeFee, Float highestRangeFee) {
        this.lowerTemperature = lowerTemperature;
        this.higherTemperature = higherTemperature;
        this.lowestRangeFee = lowestRangeFee;
        this.middleRangeFee = middleRangeFee;
        this.highestRangeFee = highestRangeFee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float lowerTemperature;
    private Float higherTemperature;
    private Float lowestRangeFee;
    private Float middleRangeFee;
    private Float highestRangeFee;

}
