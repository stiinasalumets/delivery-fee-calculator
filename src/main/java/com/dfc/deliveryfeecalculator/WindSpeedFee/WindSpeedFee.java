package com.dfc.deliveryfeecalculator.WindSpeedFee;

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
public class WindSpeedFee {

    public WindSpeedFee(Float lowerWindSpeed, Float higherWindSpeed, Float middleWindSRange, Float highestWindRange) {
        this.lowerWindSpeed = lowerWindSpeed;
        this.higherWindSpeed = higherWindSpeed;
        this.middleWindSRange = middleWindSRange;
        this.highestWindRange = highestWindRange;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float lowerWindSpeed;
    private Float higherWindSpeed;
    private Float middleWindSRange;
    private Float highestWindRange;

}
