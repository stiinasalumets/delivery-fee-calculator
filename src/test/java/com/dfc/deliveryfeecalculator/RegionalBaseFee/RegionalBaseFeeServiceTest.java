package com.dfc.deliveryfeecalculator.RegionalBaseFee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionalBaseFeeServiceTest {

    @Mock
    private RegionalBaseFeeRepository regionalBaseFeeRepository;

    @InjectMocks
    private RegionalBaseFeeService regionalBaseFeeService;

    @Test
    void testGetRegionalBaseFee_Success() {
        String cityName = "Tallinn";
        RegionalBaseFee baseFee = new RegionalBaseFee(cityName, 4F, 3.5F, 3F);
        when(regionalBaseFeeRepository.findById(cityName)).thenReturn(Optional.of(baseFee));

        ResponseEntity<RegionalBaseFee> response = regionalBaseFeeService.getRegionalBaseFee(cityName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(baseFee, response.getBody());
    }

    @Test
    void testUpdateRegionalBaseFee_Success() {
        String cityName = "Tallinn";
        RegionalBaseFee existingBaseFee = new RegionalBaseFee(cityName, 4F, 3.5F, 3F);
        RegionalBaseFee newRegionalBaseFee = new RegionalBaseFee(cityName, 5F, 4F, 3.5F);
        when(regionalBaseFeeRepository.findById(cityName)).thenReturn(Optional.of(existingBaseFee));
        when(regionalBaseFeeRepository.save(any(RegionalBaseFee.class))).thenReturn(newRegionalBaseFee);

        ResponseEntity<RegionalBaseFee> response = regionalBaseFeeService.updateRegionalBaseFee(cityName, newRegionalBaseFee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newRegionalBaseFee.getCityName(), Objects.requireNonNull(response.getBody()).getCityName());
        assertEquals(newRegionalBaseFee.getCarFee(), Objects.requireNonNull(response.getBody()).getCarFee());
        assertEquals(newRegionalBaseFee.getScooterFee(), Objects.requireNonNull(response.getBody()).getScooterFee());
        assertEquals(newRegionalBaseFee.getBikeFee(), Objects.requireNonNull(response.getBody()).getBikeFee());
    }
}
