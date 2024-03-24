package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private Float lowerTemperature;
    @Column(nullable = false)
    private Float higherTemperature;
    @Column(nullable = false)
    private Float lowestRangeFee;
    @Column(nullable = false)
    private Float middleRangeFee;
    @Column(nullable = false)
    private Float highestRangeFee;

}
