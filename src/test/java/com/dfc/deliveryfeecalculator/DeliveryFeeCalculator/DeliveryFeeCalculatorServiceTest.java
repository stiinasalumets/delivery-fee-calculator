package com.dfc.deliveryfeecalculator.DeliveryFeeCalculator;

import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFee;
import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFeeService;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFee;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFeeService;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoRepository;
import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFee;
import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFeeService;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFee;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DeliveryFeeCalculatorServiceTest {

    @Mock
    private AirTemperatureFeeService airTemperatureFeeService;

    @Mock
    private RegionalBaseFeeService regionalBaseFeeService;

    @Mock
    private WeatherInfoRepository weatherInfoRepository;

    @Mock
    private WeatherPhenomenonFeeService weatherPhenomenonFeeService;

    @Mock
    private WindSpeedFeeService windSpeedFeeService;

    @InjectMocks
    private DeliveryFeeCalculatorService deliveryFeeCalculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateDeliveryFee() {
        String cityName = "Tallinn";
        String vehicleType = "bike";
        Date date = null;

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setName(cityName);
        weatherInfo.setWmo(2);
        weatherInfo.setWind(11F);
        weatherInfo.setTemperature(-3F);
        weatherInfo.setPhenomenon("sleet");

        when(weatherInfoRepository.findTop3ByOrderByTimeDesc()).thenReturn(List.of(weatherInfo));

        AirTemperatureFee airTemperatureFee = new AirTemperatureFee();
        airTemperatureFee.setMiddleRangeFee(0.5f);
        airTemperatureFee.setLowestRangeFee(1f);
        airTemperatureFee.setHighestRangeFee(0f);
        airTemperatureFee.setLowerTemperature(-10f);
        airTemperatureFee.setHigherTemperature(0f);
        when(airTemperatureFeeService.getAirTemperatureFee()).thenReturn(ResponseEntity.ok(airTemperatureFee));

        RegionalBaseFee regionalBaseFee = new RegionalBaseFee();
        regionalBaseFee.setBikeFee(5.0f);
        when(regionalBaseFeeService.getRegionalBaseFee(cityName)).thenReturn(ResponseEntity.ok(regionalBaseFee));

        WeatherPhenomenonFee weatherPhenomenonFee = new WeatherPhenomenonFee();
        weatherPhenomenonFee.setSleetFee(1f);
        when(weatherPhenomenonFeeService.getWeatherPhenomenonFee()).thenReturn(ResponseEntity.ok(weatherPhenomenonFee));

        WindSpeedFee windSpeedFee = new WindSpeedFee();
        windSpeedFee.setLowerWindSpeed(10f);
        windSpeedFee.setHigherWindSpeed(20f);
        windSpeedFee.setLowestWindRange(0f);
        windSpeedFee.setMiddleWindRange(0.5F);
        when(windSpeedFeeService.getWindSpeedFee()).thenReturn(ResponseEntity.ok(windSpeedFee));

        ResponseEntity<Float> response = deliveryFeeCalculatorService.getDeliveryFee(cityName, vehicleType, date);

        assertEquals(7.0f, response.getBody());
    }

    @Test
    void testCalculateDeliveryFeeAtDate() {
        String cityName = "Tallinn";
        String vehicleType = "bike";
        Date date = new Date();

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setName(cityName);
        weatherInfo.setWmo(2);
        weatherInfo.setWind(11F);
        weatherInfo.setTemperature(-3F);
        weatherInfo.setPhenomenon("sleet");


        when(weatherInfoRepository.findTop3ByTimeAfterOrderByTimeAsc(date)).thenReturn(List.of(weatherInfo));

        AirTemperatureFee airTemperatureFee = new AirTemperatureFee();
        airTemperatureFee.setMiddleRangeFee(0.5f);
        airTemperatureFee.setLowestRangeFee(1f);
        airTemperatureFee.setHighestRangeFee(0f);
        airTemperatureFee.setLowerTemperature(-10f);
        airTemperatureFee.setHigherTemperature(0f);
        when(airTemperatureFeeService.getAirTemperatureFee()).thenReturn(ResponseEntity.ok(airTemperatureFee));

        RegionalBaseFee regionalBaseFee = new RegionalBaseFee();
        regionalBaseFee.setBikeFee(5.0f);
        when(regionalBaseFeeService.getRegionalBaseFee(cityName)).thenReturn(ResponseEntity.ok(regionalBaseFee));

        WeatherPhenomenonFee weatherPhenomenonFee = new WeatherPhenomenonFee();
        weatherPhenomenonFee.setSleetFee(1f);
        when(weatherPhenomenonFeeService.getWeatherPhenomenonFee()).thenReturn(ResponseEntity.ok(weatherPhenomenonFee));

        WindSpeedFee windSpeedFee = new WindSpeedFee();
        windSpeedFee.setLowerWindSpeed(10f);
        windSpeedFee.setHigherWindSpeed(20f);
        windSpeedFee.setLowestWindRange(0f);
        windSpeedFee.setMiddleWindRange(0.5F);
        when(windSpeedFeeService.getWindSpeedFee()).thenReturn(ResponseEntity.ok(windSpeedFee));

        ResponseEntity<Float> response = deliveryFeeCalculatorService.getDeliveryFee(cityName, vehicleType, date);

        assertEquals(7.0f, response.getBody());
    }


//    @Test
//    void testCalculateDeliveryFee_RepositoryEmpty() throws Exception {
//        String cityName = "Test City";
//        String vehicleType = "bike";
//        Date date = new Date();
//
//        when(weatherInfoRepository.findTop3ByOrderByTimeDesc()).thenReturn(Collections.emptyList());
//
//        // Mock other service/repository responses as needed
//
//        ResponseEntity<Float> response = deliveryFeeCalculatorService.getDeliveryFee(cityName, vehicleType, date);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
}
