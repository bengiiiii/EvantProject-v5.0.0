package com.eminpolat.evantproject.business.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SensorResponse {
    private int id;
    private int sensorId;
    private double temperature;
    private double humidity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime measurementTime;

    public SensorResponse() {
    }

    public SensorResponse(int id, int sensorId, double temperature, double humidity, LocalDateTime measurementTime) {
        this.id = id;
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.measurementTime = measurementTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }
}
