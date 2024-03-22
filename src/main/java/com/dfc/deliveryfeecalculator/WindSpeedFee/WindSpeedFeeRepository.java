package com.dfc.deliveryfeecalculator.WindSpeedFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindSpeedFeeRepository extends JpaRepository<WindSpeedFee, Long> {

}
