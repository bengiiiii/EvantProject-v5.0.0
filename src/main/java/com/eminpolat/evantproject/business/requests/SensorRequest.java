package com.eminpolat.evantproject.business.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorRequest {
    @JsonProperty("sensor_id")
    private int sensorId;
    private double temperature;
    private double humidity;

    public SensorRequest() {}

    public SensorRequest(int sensorId, double temperature, double humidity) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}