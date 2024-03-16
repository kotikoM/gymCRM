package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.service.impl.TraineeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/trainee")
public class TraineeController {
    @Autowired
    private TraineeServiceImpl traineeService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> registerTrainee(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam(required = false) Date dateOfBirth,
            @RequestParam(required = false) String address) {
        RegistrationResponseDTO dto = traineeService.registerTrainee(firstName, lastName, dateOfBirth, address);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getTraineeProfile(@RequestParam String username) {
        Map<String, Object> response = traineeService.getTraineeProfile(username);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void > deleteTrainee(@RequestParam String username) {
        traineeService.deleteTrainee(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateTraineeProfile(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Date dateOfBirth,
            @RequestParam String address,
            @RequestParam Boolean isActive) {
        traineeService.updateTrainee(userName, firstName, lastName, dateOfBirth, address, isActive);
        Map<String, Object> response = traineeService.getTraineeProfile(userName);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/activation")
    public ResponseEntity<Void> activateTrainee(@RequestParam String username, @RequestParam Boolean isActive) {
        traineeService.updateIsActive(username, isActive);
        return ResponseEntity.ok().build();
    }
}
