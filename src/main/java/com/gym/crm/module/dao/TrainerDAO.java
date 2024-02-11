package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class TrainerDAO {
    private final Map<Integer, Trainer> trainerMap = new HashMap<>();
    private int trainerIdCounter = 0;
    private static final Logger logger = LoggerFactory.getLogger(TrainerDAO.class);

    public void addTrainer(Trainer trainer) {
        trainer.setId(trainerIdCounter++);
        trainerMap.put(trainer.getId(), trainer);
        logger.info("Trainer added successfully. Trainer ID: {}", trainer.getId());
    }

    public void updateTrainer(Trainer trainer) {
        if (trainerMap.containsKey(trainer.getId())) {
            trainerMap.put(trainer.getId(), trainer);
            logger.info("Trainer updated successfully. Trainer ID: {}", trainer.getId());
        } else {
            logger.warn("Unable to update trainer. Trainer with ID {} not found.", trainer.getId());
            // Optionally, you can throw an exception or handle the case as needed.
        }
    }

    public Trainer getTrainerById(int trainerId) {
        Trainer trainer = trainerMap.get(trainerId);
        if (trainer != null) {
            logger.info("Trainer found. Trainer ID: {}", trainer.getId());
        } else {
            logger.warn("Trainer with ID {} not found.", trainerId);
        }
        return trainer;
    }
}
