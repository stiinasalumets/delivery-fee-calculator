package com.dfc.deliveryfeecalculator.WindSpeedFee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "RegionalBaseFee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WindSpeedFee {

    public WindSpeedFee(Float lowerWindSpeed, Float higherWindSpeed, Float middleWindRange, Float lowestWindRange) {
        this.lowerWindSpeed = lowerWindSpeed;
        this.higherWindSpeed = higherWindSpeed;
        this.middleWindRange = middleWindRange;
        this.lowestWindRange = lowestWindRange;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float lowerWindSpeed;
    private Float higherWindSpeed;
    private Float middleWindRange;
    private Float lowestWindRange;

}
