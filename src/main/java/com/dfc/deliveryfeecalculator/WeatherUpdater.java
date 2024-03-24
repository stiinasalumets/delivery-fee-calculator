package com.dfc.deliveryfeecalculator;

import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFee;
import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFeeService;
import com.dfc.deliveryfeecalculator.DeliveryFeeCalculatorApplication;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFee;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFeeRepository;
import com.dfc.deliveryfeecalculator.WeatherInfo.Observations;
import com.dfc.deliveryfeecalculator.WeatherInfo.Station;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoRepository;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFee;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class WeatherUpdater {

    Logger logger = LoggerFactory.getLogger(DeliveryFeeCalculatorApplication.class);
    private final WeatherInfoRepository weatherInfoRepository;
    private final AirTemperatureFeeService airTemperatureFeeService;
    private final WindSpeedFeeService windSpeedFeeService;

    @Autowired
    public WeatherUpdater(WeatherInfoRepository weatherInfoRepository, AirTemperatureFeeService airTemperatureFeeService, WindSpeedFeeService windSpeedFeeService) {
        this.weatherInfoRepository = weatherInfoRepository;
        this.airTemperatureFeeService = airTemperatureFeeService;
        this.windSpeedFeeService = windSpeedFeeService;
    }

    public WeatherInfo currentWeatherInfoInCity(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        ResponseEntity<Observations> responseEntity = restTemplate.getForEntity("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php", Observations.class);
        Observations observations = responseEntity.getBody();

        if (observations != null && observations.getStations() != null) {
            for (Station station : observations.getStations()) {
                if (station.getName().equals(cityName)) {
                    String name = station.getName();
                    Integer wmo = station.getWmo();
                    Float temperature = station.getTemperature();
                    Float wind = station.getWind();
                    String phenomenon = station.getPhenomenon();
                    return new WeatherInfo(name, wmo, temperature, wind, phenomenon);

                }
            }
        }
        return new WeatherInfo();
    }

    public void addWeatherInfo() {
        List<String> cities = List.of("Tallinn-Harku", "Tartu-Tõravere", "Pärnu");
        for (String city : cities) {
            WeatherInfo weatherInfo = currentWeatherInfoInCity(city);
            weatherInfoRepository.save(weatherInfo);
        }

    }

    @Scheduled(cron = "0 15 * * * *")
    public void scheduledAddWeatherInfo() {
        addWeatherInfo();
    }

    @Scheduled(cron = "0 * * * * *")
    public void test() {
        String vehicleType = "Scooter";
        String cityName = "Tallinn-Harku";

    }

}
