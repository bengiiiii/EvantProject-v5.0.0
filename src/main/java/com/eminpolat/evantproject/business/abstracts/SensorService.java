package com.eminpolat.evantproject.business.abstracts;

import com.eminpolat.evantproject.business.responses.SensorResponse;

import java.util.List;

public interface SensorService {
    void fetchAndSave();
    List<SensorResponse> getAllData();
    SensorResponse getLatestSensor();
}
