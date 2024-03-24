package com.dfc.deliveryfeecalculator.DeliveryFeeCalculator;

import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFee;
import com.dfc.deliveryfeecalculator.AirTemperatureFee.AirTemperatureFeeService;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFee;
import com.dfc.deliveryfeecalculator.RegionalBaseFee.RegionalBaseFeeService;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfo;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoRepository;
import com.dfc.deliveryfeecalculator.WeatherInfo.WeatherInfoService;
import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFee;
import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFeeRepository;
import com.dfc.deliveryfeecalculator.WeatherPhenomenonFee.WeatherPhenomenonFeeService;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFee;
import com.dfc.deliveryfeecalculator.WindSpeedFee.WindSpeedFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFeeCalculatorService {

    private final AirTemperatureFeeService airTemperatureFeeService;
    private final RegionalBaseFeeService regionalBaseFeeService;
    private final WeatherInfoService weatherInfoService;
    private final WeatherInfoRepository weatherInfoRepository;
    private final WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;
    private final WeatherPhenomenonFeeService weatherPhenomenonFeeService;
    private final WindSpeedFeeService windSpeedFeeService;

    @Autowired
    public DeliveryFeeCalculatorService(AirTemperatureFeeService airTemperatureFeeService, RegionalBaseFeeService regionalBaseFeeService, WeatherInfoService weatherInfoService, WeatherPhenomenonFeeService weatherPhenomenonFeeService, WindSpeedFeeService windSpeedFeeService, WeatherInfoRepository weatherInfoRepository, WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository) {
        this.airTemperatureFeeService = airTemperatureFeeService;
        this.regionalBaseFeeService = regionalBaseFeeService;
        this.weatherInfoService = weatherInfoService;
        this.weatherInfoRepository = weatherInfoRepository;
        this.weatherPhenomenonFeeService = weatherPhenomenonFeeService;
        this.windSpeedFeeService = windSpeedFeeService;
        this.weatherPhenomenonFeeRepository = weatherPhenomenonFeeRepository;
    }

    public WeatherInfo latestInfoFromCity(String cityName) throws ClassNotFoundException {
        List<WeatherInfo> latestWeatherUpdates = weatherInfoRepository.findTop3ByOrderByTimeDesc();
        for (WeatherInfo weatherInfo : latestWeatherUpdates) {
            if (weatherInfo.getName().equals(cityName)) {
                return weatherInfo;
            }
        }
        throw new ClassNotFoundException("Could not find Weather Data with city name " + cityName);
    }

    public Float calculateAirTemperatureFee(WeatherInfo weatherInfo, String vehicleType) {
        Float result = 0F;
        try {
            if (vehicleType.equals("scooter") || vehicleType.equals("bike")) {
                if (airTemperatureFeeService.getAirTemperatureFee().hasBody()) {
                    Float airTemperature = weatherInfo.getTemperature();
                    AirTemperatureFee airTemperatureFee = airTemperatureFeeService.getAirTemperatureFee().getBody();
                    Float lowerTemperature = airTemperatureFee.getLowerTemperature();
                    Float higherTemperature = airTemperatureFee.getHigherTemperature();
                    if (airTemperature <= lowerTemperature) {
                        result = airTemperatureFee.getLowestRangeFee();
                    } else if (airTemperature <= higherTemperature) {
                        result = airTemperatureFee.getMiddleRangeFee();
                    } else {
                        result = airTemperatureFee.getHighestRangeFee();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Errors in calculating Air Temperature Fee: " + e);
        }
        return result;
    }

    public Float calculateWindSpeedFee(WeatherInfo weatherInfo, String vehicleType) {
        Float result = 0F;
        try {
            if (vehicleType.equals("bike")) {
                if (windSpeedFeeService.getWindSpeedFee().hasBody()) {
                    Float windSpeed = weatherInfo.getWind();
                    WindSpeedFee windSpeedFee = windSpeedFeeService.getWindSpeedFee().getBody();
                    Float lowerWindSpeed = windSpeedFee.getLowerWindSpeed();
                    Float higherWindSpeed = windSpeedFee.getHigherWindSpeed();
                    if (windSpeed >= higherWindSpeed) {
                        throw new Exception("Usage of selected vehicle type is forbidden");
                    } else if (windSpeed >= lowerWindSpeed) {
                        result = windSpeedFee.getMiddleWindRange();
                    } else {
                        result = windSpeedFee.getLowestWindRange();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Errors in calculating Wind Speed Fee: " + e);
        }
        return result;
    }

    public Float calculateWeatherPhenomenonFee(WeatherInfo weatherInfo, String vehicleType) {
        Float result = 0F;
        try {
            if (vehicleType.equals("scooter") || vehicleType.equals("bike")) {
                WeatherPhenomenonFee weatherPhenomenonFee = weatherPhenomenonFeeService.getWeatherPhenomenonFee().getBody();
                String phenomenon = weatherInfo.getPhenomenon().toLowerCase();
                if (phenomenon.contains("rain")) {
                    result = weatherPhenomenonFee.getRainFee();
                } else if (phenomenon.contains("sleet")) {
                    result = weatherPhenomenonFee.getSleetFee();
                } else if (phenomenon.contains("snow")) {
                    result = weatherPhenomenonFee.getSnowFee();
                } else if (phenomenon.contains("glaze") || phenomenon.contains("hail") || phenomenon.contains("thunder")){
                    throw new Exception("Usage of selected vehicle type is forbidden");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Errors in calculating Weather Phenomenon Fee: " + e);
        }
        return result;
    }

    public Float calculateRegionalBaseFee(String cityName, String vehicleType){
        Float result = 0F;
        try {
            RegionalBaseFee regionalBaseFee = regionalBaseFeeService.getRegionalBaseFee(cityName).getBody();
            result = switch (vehicleType) {
                case "scooter" -> regionalBaseFee.getScooterFee();
                case "bike" -> regionalBaseFee.getBikeFee();
                case "car" -> regionalBaseFee.getCarFee();
                default -> throw new Exception("No corresponding vehicle type found");
            };
        } catch (Exception e) {
            throw new RuntimeException("Errors in calculating Weather Phenomenon Fee: " + e);
        }
        return result;
    }

    public ResponseEntity<Float> getDeliveryFee(String cityName, String vehicleType) {
        Float result = 0F;
        try {
            vehicleType = vehicleType.toLowerCase();
            WeatherInfo weatherInfo = latestInfoFromCity(cityName);
            result += calculateAirTemperatureFee(weatherInfo, vehicleType);
            result += calculateWindSpeedFee(weatherInfo, vehicleType);
            result += calculateWeatherPhenomenonFee(weatherInfo, vehicleType);
            result += calculateRegionalBaseFee(cityName, vehicleType);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

