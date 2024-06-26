package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import jakarta.persistence.Entity;
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
public class RegionalBaseFee {

    @Id
    private String CityName;
    private Float CarFee;
    private Float ScooterFee;
    private Float BikeFee;

}
