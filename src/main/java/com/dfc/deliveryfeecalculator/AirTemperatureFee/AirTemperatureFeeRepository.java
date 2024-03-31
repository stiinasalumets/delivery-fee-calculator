package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirTemperatureFeeRepository extends JpaRepository<AirTemperatureFee, Long> {

}
