package com.dfc.deliveryfeecalculator.WeatherInfo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "observations")
public class Observations {
    // Either use field or getter method, not both
    private List<Station> stations;

    @XmlElement(name = "station")
    public List<Station> getStations() {
        return stations;
    }

    // Setter method if you choose not to use field
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
