package com.dfc.deliveryfeecalculator.WeatherPhenomenonFee;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WeatherPhenomenonFeeServiceTest {

    @Test
    void testGetWeatherPhenomenonFee() {
        WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository = mock(WeatherPhenomenonFeeRepository.class);
        WeatherPhenomenonFeeService weatherPhenomenonFeeService = new WeatherPhenomenonFeeService(weatherPhenomenonFeeRepository);

        WeatherPhenomenonFee weatherPhenomenonFee = new WeatherPhenomenonFee(1.0f, 1.0f, 0.5f);
        when(weatherPhenomenonFeeRepository.findById(1L)).thenReturn(java.util.Optional.of(weatherPhenomenonFee));

        ResponseEntity<WeatherPhenomenonFee> responseEntity = weatherPhenomenonFeeService.getWeatherPhenomenonFee();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(weatherPhenomenonFee, responseEntity.getBody());
    }

    @Test
    void testUpdateWeatherPhenomenonFee() {
        WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository = mock(WeatherPhenomenonFeeRepository.class);
        WeatherPhenomenonFeeService weatherPhenomenonFeeService = new WeatherPhenomenonFeeService(weatherPhenomenonFeeRepository);

        WeatherPhenomenonFee newWeatherPhenomenonFee = new WeatherPhenomenonFee(2.0f, 2.0f, 1.0f);
        WeatherPhenomenonFee existingWeatherPhenomenonFee = new WeatherPhenomenonFee(1.0f, 1.0f, 0.5f);
        when(weatherPhenomenonFeeRepository.findById(1L)).thenReturn(java.util.Optional.of(existingWeatherPhenomenonFee));
        when(weatherPhenomenonFeeRepository.save(any(WeatherPhenomenonFee.class))).thenReturn(newWeatherPhenomenonFee);

        ResponseEntity<WeatherPhenomenonFee> responseEntity = weatherPhenomenonFeeService.updateWeatherPhenomenonFee(newWeatherPhenomenonFee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newWeatherPhenomenonFee.getRainFee(), Objects.requireNonNull(responseEntity.getBody()).getRainFee());
        assertEquals(newWeatherPhenomenonFee.getSleetFee(), Objects.requireNonNull(responseEntity.getBody()).getSleetFee());
        assertEquals(newWeatherPhenomenonFee.getSnowFee(), Objects.requireNonNull(responseEntity.getBody()).getSnowFee());
    }
}
