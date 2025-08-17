package com.eminpolat.evantproject.controller;

import com.eminpolat.evantproject.business.abstracts.SensorService;
import com.eminpolat.evantproject.business.responses.SensorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evant")
//@CrossOrigin(origins = "http://localhost:3000")
public class SensorController {
    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/getAll")
    public List<SensorResponse> getAll() {
        return sensorService.getAllData();
    }

    @GetMapping("/latest")
    public SensorResponse getLatest()
    {
        return sensorService.getLatestSensor();
    }
}
