package com.eminpolat.evantproject.business.concretes;

import com.eminpolat.evantproject.business.abstracts.SensorService;
import com.eminpolat.evantproject.business.requests.SensorRequest;
import com.eminpolat.evantproject.business.responses.SensorResponse;
import com.eminpolat.evantproject.core.utilities.mapper.SensorMapperManager;
import com.eminpolat.evantproject.dataAccess.SensorRepository;
import com.eminpolat.evantproject.entites.Sensor;
import lombok.extern.slf4j.Slf4j;             
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j                                      
@Service
public class SensorManager implements SensorService {
    private final SensorRepository sensorRepository;
    private final RestTemplate restTemplate;
    private final String url =
        "https://isi-nem-88c9f-default-rtdb.europe-west1.firebasedatabase.app/sensor_data/sensor101.json";

    public SensorManager(SensorRepository sensorRepository, RestTemplate restTemplate) {
        this.sensorRepository = sensorRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void fetchAndSave() {
        try {
            log.info("⏳ Firebase'e istek: {}", url);                  
            SensorRequest dto = restTemplate.getForObject(url, SensorRequest.class);
            if (dto == null) {
                log.warn("⚠️ Firebase null döndü");                  
                return;
            }
            Sensor sensor = new Sensor();
            sensor.setSensorId(dto.getSensorId());
            sensor.setTemperature(dto.getTemperature());
            sensor.setHumidity(dto.getHumidity());
            sensor.setMeasurementTime(LocalDateTime.now());

            sensorRepository.save(sensor);
            log.info("✅ Kaydedildi: id={}, t={}°C, h={}%",             
                    dto.getSensorId(), dto.getTemperature(), dto.getHumidity());
        } catch (Exception e) {
            log.error("❌ Veri çekme hatası", e);                      
        }
    }

    @Override
    public List<SensorResponse> getAllData() {
        return sensorRepository.findAll().stream()
                .map(SensorMapperManager::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SensorResponse getLatestSensor() {
        Sensor latestSensor = sensorRepository.findTopByOrderByMeasurementTimeDesc();
        return SensorMapperManager.toDto(latestSensor);
    }
}



