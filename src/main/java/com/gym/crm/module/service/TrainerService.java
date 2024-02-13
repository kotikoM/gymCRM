package com.gym.crm.module.service;

import com.gym.crm.module.dao.TrainerDAO;
import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.domain.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    private TrainerDAO trainerDAO;

    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);

    public void addTrainer(TrainingType specialization, int userId) {
        logger.info("Adding a new trainer...");
        Trainer trainer = new Trainer(specialization, userId);
        trainerDAO.addTrainer(trainer);
        logger.info("Trainer added successfully. Trainer ID: {}", trainer.getId());
    }

    public void updateTrainer(Trainer trainer) {
        logger.info("Updating trainer with ID: {}", trainer.getId());
        trainerDAO.updateTrainer(trainer);
        logger.info("Trainer updated successfully.");
    }

    public Trainer getTrainerById(int trainerId) {
        logger.info("Fetching trainer with ID: {}", trainerId);
        Trainer trainer = trainerDAO.getTrainerById(trainerId);
        if (trainer != null) {
            logger.info("Trainer found: {}", trainer);
        } else {
            logger.warn("Trainer with ID {} not found", trainerId);
        }
        return trainer;
    }

    public void clear(){
        this.trainerDAO.clear();
    }

}
