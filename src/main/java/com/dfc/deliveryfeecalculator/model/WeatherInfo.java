package com.dfc.deliveryfeecalculator.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name="WeatherInfo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer wmo;
    private Float temperature;
    private Float wind;
    private String phenomenon;
    private Integer time;
}
