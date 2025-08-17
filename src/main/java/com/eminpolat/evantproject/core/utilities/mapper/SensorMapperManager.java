package com.eminpolat.evantproject.core.utilities.mapper;

import com.eminpolat.evantproject.business.responses.SensorResponse;
import com.eminpolat.evantproject.entites.Sensor;

public class SensorMapperManager {
    public static SensorResponse toDto(Sensor entity) {
        return new SensorResponse(
                entity.getId(),
                entity.getSensorId(),
                entity.getTemperature(),
                entity.getHumidity(),
                entity.getMeasurementTime()
        );
    }

    public static Sensor toEntity(SensorResponse dto) {
        return new Sensor(
                dto.getId(),
                dto.getSensorId(),
                dto.getTemperature(),
                dto.getHumidity(),
                dto.getMeasurementTime()
        );
    }
}
