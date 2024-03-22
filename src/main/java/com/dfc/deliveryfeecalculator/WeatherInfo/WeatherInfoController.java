package com.dfc.deliveryfeecalculator.WeatherInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/WeatherInfo")
public class WeatherInfoController {
    private final WeatherInfoService weatherInfoService;

    @Autowired
    public WeatherInfoController(WeatherInfoService weatherInfoService) {
        this.weatherInfoService = weatherInfoService;
    }

    @GetMapping("/getAllWeatherInfo")
    public ResponseEntity<List<WeatherInfo>> getAllWeatherInfo(){
        return weatherInfoService.getAllWeatherInfo();
    }

    @GetMapping("/getWeatherInfoById/{id}")
    public ResponseEntity<WeatherInfo> getWeatherInfoById(@PathVariable Long id){
        return weatherInfoService.getWeatherInfoById(id);
    }

    @PostMapping("/addWeatherInfo")
    public ResponseEntity<WeatherInfo> addWeatherInfo(@RequestBody WeatherInfo weatherInfo) {
        return weatherInfoService.addWeatherInfo(weatherInfo);
    }

    @PostMapping("/updateWeatherInfoById/{id}")
    public ResponseEntity<WeatherInfo> updateWeatherInfoById(@PathVariable Long id, @RequestBody WeatherInfo newWeatherInfoData) {
        return weatherInfoService.updateWeatherInfoById(id, newWeatherInfoData);
    }

    @DeleteMapping("/deleteWeatherInfoById/{id}")
    public ResponseEntity<HttpStatus> deleteWeatherInfoById(@PathVariable Long id) {
        return weatherInfoService.deleteWeatherInfoById(id);
    }
}
