package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherInfoService {

    private final WeatherInfoRepository weatherInfoRepository;

    @Autowired
    public WeatherInfoService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }

    public ResponseEntity<List<WeatherInfo>> getAllWeatherInfo() {
        try {
            List<WeatherInfo> weatherInfoList = new ArrayList<>(weatherInfoRepository
                    .findAll());
            if (weatherInfoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(weatherInfoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WeatherInfo> getWeatherInfoById(@PathVariable Long id) {
        try {
            Optional<WeatherInfo> weatherInfoOptional = weatherInfoRepository.findById(id);
            return weatherInfoOptional.map(weatherInfo -> new ResponseEntity<>(weatherInfo, HttpStatus.OK)).orElseGet(()
                    -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<WeatherInfo> addWeatherInfo(@RequestBody WeatherInfo weatherInfo) {
        try {
            WeatherInfo weatherInfoObject = weatherInfoRepository.save(weatherInfo);
            return new ResponseEntity<>(weatherInfoObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
