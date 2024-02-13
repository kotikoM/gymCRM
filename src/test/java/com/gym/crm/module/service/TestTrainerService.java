package com.gym.crm.module.service;

import com.gym.crm.module.dao.TrainerDAO;
import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.domain.TrainingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestTrainerService {
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TrainerDAO trainerDAO;

    @Test
    void testAddTrainer() {
        TrainingType specialization = TrainingType.BOXING;
        int userId = 1;
        trainerService.addTrainer(specialization, userId);
        Trainer addedTrainer = trainerDAO.getTrainerById(2);

        Assertions.assertNotNull(addedTrainer);
        assertEquals(specialization, addedTrainer.getSpecialization());
        assertEquals(userId, addedTrainer.getUserId());
    }

    @Test
    void testUpdateTrainer() {
        TrainingType originalSpecialization = TrainingType.BODYBUILDING;
        int originalUserId = 1;
        trainerService.addTrainer(originalSpecialization, originalUserId);
        TrainingType updatedSpecialization = TrainingType.CARDIO;
        int updatedUserId = 2;
        Trainer trainerToUpdate = trainerDAO.getTrainerById(0);
        trainerToUpdate.setSpecialization(updatedSpecialization);
        trainerToUpdate.setUserId(updatedUserId);
        trainerService.updateTrainer(trainerToUpdate);
        Trainer updatedTrainer = trainerDAO.getTrainerById(0);

        Assertions.assertNotNull(updatedTrainer);
        assertEquals(updatedSpecialization, updatedTrainer.getSpecialization());
        assertEquals(updatedUserId, updatedTrainer.getUserId());
    }

    @Test
    void testGetTrainerById() {
        TrainingType specialization = TrainingType.BOXING;
        int userId = 3;
        trainerService.addTrainer(specialization, userId);
        Trainer retrievedTrainer = trainerService.getTrainerById(1);

        Assertions.assertNotNull(retrievedTrainer);
        assertEquals(specialization, retrievedTrainer.getSpecialization());
        assertEquals(userId, retrievedTrainer.getUserId());
    }

    @Test
    void testGetTrainerById_NotFound() {
        Trainer retrievedTrainer = trainerService.getTrainerById(999);
        Assertions.assertNull(retrievedTrainer);
    }

}
