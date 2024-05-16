package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.DTO.TraineeProfileDTO;
import com.gym.crm.module.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Date;

@Controller
@RequestMapping("/trainee")
public class TraineeController {
    @Autowired
    private TraineeService traineeService;

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
    public ResponseEntity<TraineeProfileDTO> getTraineeProfile(@RequestParam String username) {
        TraineeProfileDTO response = traineeService.getTraineeProfile(username);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void > deleteTrainee(@RequestParam String username) {
        traineeService.deleteTrainee(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TraineeProfileDTO> updateTraineeProfile(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Date dateOfBirth,
            @RequestParam String address,
            @RequestParam Boolean isActive) {
        traineeService.updateTrainee(userName, firstName, lastName, dateOfBirth, address, isActive);
        TraineeProfileDTO response = traineeService.getTraineeProfile(userName);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/activation")
    public ResponseEntity<Void> activateTrainee(@RequestParam String username, @RequestParam Boolean isActive) {
        traineeService.updateActivity(username, isActive);
        return ResponseEntity.ok().build();
    }
}
