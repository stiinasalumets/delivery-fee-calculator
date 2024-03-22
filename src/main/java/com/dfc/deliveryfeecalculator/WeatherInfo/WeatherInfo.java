package com.dfc.deliveryfeecalculator.WeatherInfo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Entity
@Table(name="WeatherInfo")
@Setter
@Getter
@ToString
public class WeatherInfo {

    public WeatherInfo(String name, Integer wmo, Float temperature, Float wind, String phenomenon) {
        this.name = name;
        this.wmo = wmo;
        this.temperature = temperature;
        this.wind = wind;
        this.phenomenon = phenomenon;
        this.time = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer wmo;
    private Float temperature;
    private Float wind;
    private String phenomenon;
    private Date time;
}
