package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.DTO.TrainerProfileDTO;
import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> registerTrainer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Integer trainingTypeId) {
        RegistrationResponseDTO dto = trainerService.registerTrainer(firstName, lastName, trainingTypeId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/profile")
    public ResponseEntity<TrainerProfileDTO> getTrainerProfile(@RequestParam String username) {
        TrainerProfileDTO response = trainerService.getTrainerProfile(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<Trainer>> getUnassignedTrainers(@RequestParam String traineeUsername) {
        List<Trainer> trainers = trainerService.getUnassignedTrainers(traineeUsername);
        return ResponseEntity.ok(trainers);
    }

    @PutMapping
    public ResponseEntity<TrainerProfileDTO> updateTrainerProfile(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Integer specialization,
            @RequestParam Boolean isActive) {
        trainerService.updateTrainer(userName, firstName, lastName, specialization, isActive);
        TrainerProfileDTO trainerProfile = trainerService.getTrainerProfile(userName);
        return ResponseEntity.ok(trainerProfile);
    }

    @PatchMapping("/activation")
    public ResponseEntity<Void> activateTrainer(@RequestParam String username, @RequestParam Boolean isActive) {
        trainerService.updateActivity(username, isActive);
        return ResponseEntity.ok().build();
    }
}
