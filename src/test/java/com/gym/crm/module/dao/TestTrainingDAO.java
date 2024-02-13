package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Training;
import com.gym.crm.module.domain.TrainingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTrainingDAO {

    private TrainingDAO trainingDAO = new TrainingDAO();

    @Test
    void testAddTraining() {
        Training training = createTrainingInstance();
        trainingDAO.addTraining(training);

        Training retrievedTraining = trainingDAO.getTrainingById(training.getId());

        assertNotNull(retrievedTraining, "Training should not be null");
        assertEquals(training, retrievedTraining);
    }

    @Test
    void testGetTrainingById() {
        // Add a training to the DAO
        Training training1 = createTrainingInstance();
        trainingDAO.addTraining(training1);
        Training retrievedTraining1 = trainingDAO.getTrainingById(training1.getId());
        Training training2 = createTrainingInstance();
        trainingDAO.addTraining(training2);
        Training retrievedTraining2 = trainingDAO.getTrainingById(training2.getId());

        assertNotNull(retrievedTraining1);
        assertEquals(training1, retrievedTraining1);
        assertNotNull(retrievedTraining2);
        assertEquals(training2, retrievedTraining2);
    }

    @Test
    void testGetTrainingByIdNotFound() {
        // Attempt to retrieve a non-existent training
        Training retrievedTraining = trainingDAO.getTrainingById(999);

        assertNull(retrievedTraining, "Retrieved training should be null for non-existent ID");
    }

    private Training createTrainingInstance() {
        int traineeId = 1;
        int trainerId = 2;
        String trainingName = "Test Training";
        TrainingType trainingType = TrainingType.CARDIO;
        Date trainingDate = new Date();
        Number trainingDuration = 60;

        return new Training(traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);
    }
}
