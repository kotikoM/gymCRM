package com.gym.crm.module.controller;

import com.gym.crm.module.DTO.TraineeRegistrationResponseDTO;
import com.gym.crm.module.service.TraineeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/trainee")
public class TraineeController {
    @Autowired
    private TraineeServiceImpl traineeService;

    @PostMapping("/register")
    public ResponseEntity<TraineeRegistrationResponseDTO> registerTrainee(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam(required = false) Date dateOfBirth,
            @RequestParam(required = false) String address
            ) {
        TraineeRegistrationResponseDTO dto = traineeService.createTrainee(firstName, lastName, dateOfBirth, address);
        return ResponseEntity.ok(dto);
    }

}
