package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.service.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
