package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.domain.TrainingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTrainerDAO {
    private TrainerDAO trainerDAO = new TrainerDAO();

    @Test
    void testAddTrainer() {
        Trainer trainer = new Trainer(TrainingType.BODYBUILDING, 1);
        trainerDAO.addTrainer(trainer);

        assertEquals(trainer, trainerDAO.getTrainerById(trainer.getId()));
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer(TrainingType.BODYBUILDING, 1);
        trainerDAO.addTrainer(trainer);
        Trainer updatedTrainer = new Trainer(TrainingType.BOXING, 1);
        updatedTrainer.setId(0);
        trainerDAO.updateTrainer(updatedTrainer);

        assertEquals(TrainingType.BOXING,
                trainerDAO.getTrainerById(0).getSpecialization());
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer1 = new Trainer(TrainingType.BODYBUILDING, 1);
        Trainer trainer2 = new Trainer(TrainingType.BOXING, 2);
        Trainer trainer3 = new Trainer(TrainingType.CARDIO, 3);
        trainerDAO.addTrainer(trainer1);
        trainerDAO.addTrainer(trainer2);
        trainerDAO.addTrainer(trainer3);

        assertAll(
                () -> assertEquals(trainer1, trainerDAO.getTrainerById(trainer1.getId())),
                () -> assertEquals(trainer2, trainerDAO.getTrainerById(trainer2.getId())),
                () -> assertEquals(trainer3, trainerDAO.getTrainerById(trainer3.getId()))
        );
    }
}
