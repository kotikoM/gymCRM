package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.service.impl.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerServiceImpl trainerService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> registerTrainer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Integer trainingTypeId) {
        RegistrationResponseDTO dto = trainerService.registerTrainer(firstName, lastName, trainingTypeId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getTrainerProfile(@RequestParam String username) {
        Map<String, Object> response = trainerService.getTrainerProfile(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<Trainer>> getUnassignedTrainers(@RequestParam String traineeUsername) {
        List<Trainer> trainers = trainerService.getUnassignedTrainers(traineeUsername);
        return ResponseEntity.ok(trainers);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateTrainerProfile(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Integer specialization,
            @RequestParam Boolean isActive) {
        trainerService.updateTrainer(userName, firstName, lastName, specialization, isActive);
        Map<String, Object> trainerProfile = trainerService.getTrainerProfile(userName);
        if (trainerProfile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(trainerProfile);
    }

    @PatchMapping("/activation")
    public ResponseEntity<Void> activateTrainer(@RequestParam String username, @RequestParam Boolean isActive) {
        trainerService.updateIsActive(username, isActive);
        return ResponseEntity.ok().build();
    }
}
