package com.gym.crm.module.service;

import com.gym.crm.module.dao.TrainingDAO;
import com.gym.crm.module.domain.Training;
import com.gym.crm.module.domain.TrainingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTrainingService {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingDAO trainingDAO;

    @Test
    void testAddTraining() {
        int traineeId = 1;
        int trainerId = 2;
        String trainingName = "Cardio Workout";
        TrainingType trainingType = TrainingType.CARDIO;
        Date trainingDate = new Date();
        Number trainingDuration = 60;
        trainingService.addTraining(traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);

        Training addedTraining = trainingDAO.getTrainingById(1);
        assertNotNull(addedTraining);
        assertEquals(traineeId, addedTraining.getTraineeId());
        assertEquals(trainerId, addedTraining.getTrainerId());
        assertEquals(trainingName, addedTraining.getTrainingName());
        assertEquals(trainingType, addedTraining.getTrainingType());
        assertEquals(trainingDate, addedTraining.getTrainingDate());
        assertEquals(trainingDuration, addedTraining.getTrainingDuration());
    }

    @Test
    void testGetTrainingById() {
        int traineeId = 1;
        int trainerId = 2;
        String trainingName = "Cardio Workout";
        TrainingType trainingType = TrainingType.CARDIO;
        Date trainingDate = new Date();
        Number trainingDuration = 60;
        trainingService.addTraining(traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);
        Training retrievedTraining = trainingService.getTrainingById(0);

        assertNotNull(retrievedTraining);
        assertEquals(traineeId, retrievedTraining.getTraineeId());
        assertEquals(trainerId, retrievedTraining.getTrainerId());
        assertEquals(trainingName, retrievedTraining.getTrainingName());
        assertEquals(trainingType, retrievedTraining.getTrainingType());
        assertEquals(trainingDate, retrievedTraining.getTrainingDate());
        assertEquals(trainingDuration, retrievedTraining.getTrainingDuration());
    }

    @Test
    void testGetTrainingById_NotFound() {
        Training retrievedTraining = trainingService.getTrainingById(999);
        assertNull(retrievedTraining);
    }

}
