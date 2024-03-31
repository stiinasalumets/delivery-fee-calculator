package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

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
public class WeatherPhenomenonFee {

    public WeatherPhenomenonFee(Float rainFee, Float sleetFee, Float snowFee) {
        this.rainFee = rainFee;
        this.sleetFee = sleetFee;
        this.snowFee = snowFee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float rainFee;
    private Float sleetFee;
    private Float snowFee;

}
