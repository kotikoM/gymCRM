package com.gym.crm.module.controller;

import com.gym.crm.module.actuator.CustomHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/actuator/health"))
public class HealthIndicator {
    @Autowired
    private CustomHealthIndicator healthIndicator;

    @GetMapping
    public ResponseEntity<Health> health() {
        return new ResponseEntity<>(healthIndicator.health(), HttpStatus.OK);
    }
}
