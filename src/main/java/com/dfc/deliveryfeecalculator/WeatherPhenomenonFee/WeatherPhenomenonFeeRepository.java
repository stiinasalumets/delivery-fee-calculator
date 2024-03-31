package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherPhenomenonFeeRepository extends JpaRepository<WeatherPhenomenonFee, Long> {

}
