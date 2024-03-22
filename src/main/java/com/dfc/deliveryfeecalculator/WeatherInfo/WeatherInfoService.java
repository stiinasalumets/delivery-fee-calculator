package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<List<WeatherInfo>> getAllWeatherInfo(){
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

    public ResponseEntity<WeatherInfo> getWeatherInfoById(@PathVariable Long id){
        try {
            Optional<WeatherInfo> weatherInfoOptional =  weatherInfoRepository.findById(id);
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

    public ResponseEntity<WeatherInfo> updateWeatherInfoById(@PathVariable Long id, @RequestBody WeatherInfo newWeatherInfoData) {
        try {
            Optional<WeatherInfo> oldWeatherInfoData = weatherInfoRepository.findById(id);
            if (oldWeatherInfoData.isPresent()) {
                WeatherInfo updatedWeatherInfo = oldWeatherInfoData.get();
                updatedWeatherInfo.setWmo(newWeatherInfoData.getWmo());
                updatedWeatherInfo.setWind(newWeatherInfoData.getWind());
                updatedWeatherInfo.setTime(newWeatherInfoData.getTime());
                updatedWeatherInfo.setName(newWeatherInfoData.getName());
                updatedWeatherInfo.setPhenomenon(newWeatherInfoData.getPhenomenon());
                updatedWeatherInfo.setTemperature(newWeatherInfoData.getTemperature());

                WeatherInfo weatherInfoObject = weatherInfoRepository.save(updatedWeatherInfo);
                return new ResponseEntity<>(weatherInfoObject, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteWeatherInfoById(@PathVariable Long id) {
        try {
            weatherInfoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
