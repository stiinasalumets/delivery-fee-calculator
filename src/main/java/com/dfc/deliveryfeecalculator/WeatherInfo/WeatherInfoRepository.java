package com.dfc.deliveryfeecalculator.WeatherInfo;

import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

}
