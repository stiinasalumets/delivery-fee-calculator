package com.dfc.deliveryfeecalculator.WindSpeedFee;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WindSpeedFeeServiceTest {

    @Test
    void testGetWindSpeedFee() {
        WindSpeedFeeRepository windSpeedFeeRepository = mock(WindSpeedFeeRepository.class);
        WindSpeedFeeService windSpeedFeeService = new WindSpeedFeeService(windSpeedFeeRepository);

        WindSpeedFee windSpeedFee = new WindSpeedFee(10.0f, 20.0f, 0.5f, 0.0f);
        when(windSpeedFeeRepository.findById(1L)).thenReturn(java.util.Optional.of(windSpeedFee));

        ResponseEntity<WindSpeedFee> responseEntity = windSpeedFeeService.getWindSpeedFee();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(windSpeedFee, responseEntity.getBody());
    }

    @Test
    void testUpdateWindSpeedFee() {
        WindSpeedFeeRepository windSpeedFeeRepository = mock(WindSpeedFeeRepository.class);
        WindSpeedFeeService windSpeedFeeService = new WindSpeedFeeService(windSpeedFeeRepository);

        WindSpeedFee newWindSpeedFee = new WindSpeedFee(15.0f, 25.0f, 0.75f, 0.0f);
        WindSpeedFee existingWindSpeedFee = new WindSpeedFee(10.0f, 20.0f, 0.5f, 0.0f);
        when(windSpeedFeeRepository.findById(1L)).thenReturn(java.util.Optional.of(existingWindSpeedFee));
        when(windSpeedFeeRepository.save(any(WindSpeedFee.class))).thenReturn(newWindSpeedFee);

        ResponseEntity<WindSpeedFee> responseEntity = windSpeedFeeService.updateWindSpeedFee(newWindSpeedFee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newWindSpeedFee.getLowerWindSpeed(), Objects.requireNonNull(responseEntity.getBody()).getLowerWindSpeed());
        assertEquals(newWindSpeedFee.getMiddleWindRange(), Objects.requireNonNull(responseEntity.getBody()).getMiddleWindRange());
        assertEquals(newWindSpeedFee.getHigherWindSpeed(), Objects.requireNonNull(responseEntity.getBody()).getHigherWindSpeed());
        assertEquals(newWindSpeedFee.getLowestWindRange(), Objects.requireNonNull(responseEntity.getBody()).getLowestWindRange());
    }
}
