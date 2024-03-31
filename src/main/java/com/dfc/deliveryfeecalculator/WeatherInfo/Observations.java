package com.dfc.deliveryfeecalculator.WeatherInfo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "observations")
public class Observations {
    private List<Station> stations;

    @XmlElement(name = "station")
    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
