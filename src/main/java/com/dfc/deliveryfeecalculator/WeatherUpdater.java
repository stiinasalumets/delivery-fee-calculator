package com.dfc.deliveryfeecalculator;

import com.dfc.deliveryfeecalculator.WeatherInfo.Observations;
import com.dfc.deliveryfeecalculator.WeatherInfo.Station;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@EnableScheduling
public class WeatherUpdater {

    private final WeatherInfoRepository weatherInfoRepository;

    @Autowired
    public WeatherUpdater(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
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
