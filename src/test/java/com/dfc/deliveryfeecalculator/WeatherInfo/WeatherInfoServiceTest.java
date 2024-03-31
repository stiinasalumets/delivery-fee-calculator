package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherInfoServiceTest {

    @Mock
    private WeatherInfoRepository weatherInfoRepository;

    @InjectMocks
    private WeatherInfoService weatherInfoService;

    @Test
    void testGetAllWeatherInfo_NoContent() {
        // Mock repository to return an empty list
        when(weatherInfoRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method under test
        ResponseEntity<List<WeatherInfo>> response = weatherInfoService.getAllWeatherInfo();

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetWeatherInfoById_NotFound() {
        // Mock repository to return empty optional
        Long id = 1L;
        when(weatherInfoRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method under test
        ResponseEntity<WeatherInfo> response = weatherInfoService.getWeatherInfoById(id);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllWeatherInfo() {
        // Mocking weather info data
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        weatherInfoList.add(new WeatherInfo("Tallinn", 123, 20.0f, 10.0f, "Sunny"));
        when(weatherInfoRepository.findAll()).thenReturn(weatherInfoList);

        // Call the service method
        ResponseEntity<List<WeatherInfo>> response = weatherInfoService.getAllWeatherInfo();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Tallinn", response.getBody().get(0).getName());
    }

    @Test
    void testGetWeatherInfoById() {
        // Mocking weather info data
        WeatherInfo weatherInfo = new WeatherInfo("Tallinn", 123, 20.0f, 10.0f, "Sunny");
        when(weatherInfoRepository.findById(1L)).thenReturn(Optional.of(weatherInfo));

        // Call the service method
        ResponseEntity<WeatherInfo> response = weatherInfoService.getWeatherInfoById(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tallinn", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    void testAddWeatherInfo() {
        // Mocking weather info data
        WeatherInfo weatherInfo = new WeatherInfo("Tallinn", 123, 20.0f, 10.0f, "Sunny");
        when(weatherInfoRepository.save(any(WeatherInfo.class))).thenReturn(weatherInfo);

        // Call the service method
        ResponseEntity<WeatherInfo> response = weatherInfoService.addWeatherInfo(weatherInfo);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tallinn", Objects.requireNonNull(response.getBody()).getName());
    }
}
