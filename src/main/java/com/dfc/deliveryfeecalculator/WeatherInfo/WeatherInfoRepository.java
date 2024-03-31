package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

    List<WeatherInfo> findTop3ByOrderByTimeDesc();
    List<WeatherInfo> findTop3ByTimeAfterOrderByTimeAsc(Date date);

}
