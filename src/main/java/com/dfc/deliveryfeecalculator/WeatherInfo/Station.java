package com.dfc.deliveryfeecalculator.WeatherInfo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "station")
public class Station {
    private String name;
    private Integer wmo;
    private String phenomenon;
    private Float temperature;
    private Float wind;



    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "wmocode")
    public Integer getWmo() {
        return wmo;
    }

    public void setWmo(Integer wmo) {
        this.wmo = wmo;
    }

    @XmlElement(name = "phenomenon")
    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    @XmlElement(name = "airtemperature")
    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    @XmlElement(name = "windspeed")
    public Float getWind() {
        return wind;
    }

    public void setWind(Float wind) {
        this.wind = wind;
    }
}