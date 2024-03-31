# Delivery Fee Calculator Application

## Data Source and CronJob

This application fetches weather data from the Estonian Environment Agency's weather portal at [observations.php](https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php) hourly, precisely at HH:15, using a configurable scheduled task (CronJob). The fetched data includes air temperature, wind speed, weather phenomenon, and the timestamp of observations. This data is then inserted into the database for the following stations:
- Tallinn-Harku (city of Tallinn)
- Tartu-Tõravere (city of Tartu)
- Pärnu (city of Pärnu)

The history of imported weather data is permanently stored in the database, with new entries added after each import process, ensuring no overwrite of existing entries for each station.

## Delivery Fee Calculation

The delivery fee is calculated based on input parameters from REST interface requests, weather data from the database, and predefined business rules.

### Business Rules for Regional Base Fee (RBF):

- **Tallinn:**
    - Car: RBF = €4
    - Scooter: RBF = €3.5
    - Bike: RBF = €3
- **Tartu:**
    - Car: RBF = €3.5
    - Scooter: RBF = €3
    - Bike: RBF = €2.5
- **Pärnu:**
    - Car: RBF = €3
    - Scooter: RBF = €2.5
    - Bike: RBF = €2

### Business Rules for Extra Fees for Weather Conditions:

- **Additional fees based on air temperature (ATEF) and wind speed (WSEF) are applied for specific vehicle types and weather conditions.**
- **ATEF:**
    - Air temperature less than -10°C: ATEF = €1
    - Air temperature between -10°C and 0°C: ATEF = €0.5
- **WSEF (for Bike):**
    - Wind speed between 10 m/s and 20 m/s: WSEF = €0.5
    - Wind speed greater than 20 m/s: Error message "Usage of selected vehicle type is forbidden"
- **Extra fee based on weather phenomenon (WPEF):**
    - Snow or sleet: WPEF = €1
    - Rain: WPEF = €0.5
    - Glaze, hail, or thunder: Error message "Usage of selected vehicle type is forbidden"

The application's REST interface allows requests for delivery fees based on input parameters, including city and vehicle type. The response provides the total delivery fee calculated according to the described business rules or an appropriate error message.

For enhanced functionality, the application allows managing (CRUD operations) base fees and extra fees through the REST interface. Additionally, datetime parameters can be included in the request to calculate delivery fees based on historical weather conditions.


### AirTemperatureFee

#### Update Air Temperature Fee
- **Request:** PUT request to `localhost:8080/api/v1/AirTemperatureFee/updateAirTemperatureFee`
- **Description:** Updates the air temperature fee.
- **Input:** JSON payload containing an `airTemperatureFee` element.

#### Get All Air Temperature Fees
- **Request:** PUT request to `localhost:8080/api/v1/AirTemperatureFee/getAllAirTemperatureFee`
- **Description:** Retrieves all air temperature fees.
- **Output:** Returns the air temperature fee.

---

### RegionalBaseFee

#### Update Regional Base Fee
- **Request:** PUT request to `localhost:8080/api/v1/RegionalBaseFee/updateRegionalBaseFee/{cityName}`
- **Description:** Updates the regional base fee for the specified city.
- **Input:** JSON payload containing a `regionalBaseFee` element with a path variable of the city name.

#### Get Regional Base Fee by City
- **Request:** GET request to `localhost:8080/api/v1/RegionalBaseFee/getAllRegionalBaseFee/{cityName}`
- **Description:** Retrieves the regional base fee for the specified city.
- **Input:** Path variable of the city name.
- **Output:** Returns the regional base fee.

---

### WeatherPhenomenonFee

#### Update Weather Phenomenon Fee
- **Request:** PUT request to `localhost:8080/api/v1/WeatherPhenomenonFee/updateWeatherPhenomenonFee`
- **Description:** Updates the weather phenomenon fee.
- **Input:** JSON payload containing a `WeatherPhenomenonFee` element.

#### Get Weather Phenomenon Fee
- **Request:** GET request to `localhost:8080/api/v1/WeatherPhenomenonFee/getWeatherPhenomenonFee`
- **Description:** Retrieves the weather phenomenon fee.
- **Output:** Returns the weather phenomenon fee.

---

### WindSpeedFee

#### Get All Wind Speed Fees
- **Request:** GET request to `localhost:8080/api/v1/WindSpeedFee/getAllWindSpeedFee`
- **Description:** Retrieves all wind speed fees.
- **Output:** Returns a wind speed fee element.

#### Update Wind Speed Fee
- **Request:** PUT request to `localhost:8080/api/v1/WindSpeedFee/updateWindSpeedFee`
- **Description:** Updates the wind speed fee.
- **Input:** JSON payload containing a `WindSpeedFee` element.

---

### DeliveryFeeCalculator

#### Get Delivery Fee
- **Request:** GET request to `localhost:8080/getDeliveryFee`
- **Required Parameters:** String city, String vehicle
- **Optional Parameter:** Date date in format "yyyy-MM-dd-HH"
