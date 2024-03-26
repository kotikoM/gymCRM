package com.gym.crm.module.controller;

import com.gym.crm.module.entity.Training;
import com.gym.crm.module.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        Training createdTraining = trainingService.createTraining(training);
        return ResponseEntity.ok(createdTraining);
    }

    @GetMapping("/trainee")
    public ResponseEntity<List<Training>> getTraineeTrainings(
            @RequestParam String userName,
            @RequestParam Date fromDate,
            @RequestParam Date toDate,
            @RequestParam String trainerName,
            @RequestParam Integer trainingTypeId) {
        List<Training> traineeTrainingsByCriteria = trainingService.getTraineeTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
        return ResponseEntity.ok(traineeTrainingsByCriteria);
    }

    @GetMapping("/trainer")
    public ResponseEntity<List<Training>> getTrainerTrainings(
            @RequestParam String userName,
            @RequestParam Date fromDate,
            @RequestParam Date toDate,
            @RequestParam String trainerName,
            @RequestParam Integer trainingTypeId) {
        List<Training> trainerTrainingsByCriteria = trainingService.getTrainerTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
        return ResponseEntity.ok(trainerTrainingsByCriteria);
    }
}
