## AirTemperatureFee

PUT request: localhost:8080/api/v1/AirTemperatureFee/updateAirTemperatureFee
it takes in an airTemperatureFee element

PUT request: localhost:8080/api/v1/AirTemperatureFee/getAllAirTemperatureFee
returns the AirTemperatureFee

## RegionalBaseFee

PUT request: localhost:8080/api/v1/RegionalBaseFee/updateRegionalBaseFee/{cityName}
it takes in a regionalBaseFee element with a path-variable of the city name

GET request: localhost:8080/api/v1/RegionalBaseFee//getAllRegionalBaseFee/{cityName}
it takes in a path-variable of the city name and returns RegionalBaseFee

## WeatherPhenomenonFee

PUT request: localhost:8080/api/v1/WeatherPhenomenonFee/updateWeatherPhenomenonFee
it takes in an WeatherPhenomenonFee element

GET request: localhost:8080/api/v1/WeatherPhenomenonFee/getWeatherPhenomenonFee
it returns a WeatherPhenomenonFee element

## WindSpeedFee

GET request: localhost:8080/api/v1/WindSpeedFee/getAllWindSpeedFee
returns a WindSpeedFee element

PUT request: localhost:8080/api/v1/WindSpeedFee/updateWindSpeedFee
it takes in an WindSpeedFee element

## DeliveryFeeCalculator

GET request: localhost:8080/getDeliveryFee
required parameters: String city, String vehicle and optional parameter Date date in format "yyyy-MM-dd-HH"