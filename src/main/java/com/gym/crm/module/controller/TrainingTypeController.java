package com.gym.crm.module.controller;

import com.gym.crm.module.entity.TrainingType;
import com.gym.crm.module.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trainingtype")
public class TrainingTypeController {
    @Autowired
    private TrainingTypeService trainingTypeService;

    @GetMapping
    public ResponseEntity<List<TrainingType>> getAllTrainingTypes() {
        List<TrainingType> allTrainingTypes = trainingTypeService.getAllTrainingTypes();
        return ResponseEntity.ok(allTrainingTypes);
    }
}
