package com.dfc.deliveryfeecalculator.repository;

import com.dfc.deliveryfeecalculator.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

}
