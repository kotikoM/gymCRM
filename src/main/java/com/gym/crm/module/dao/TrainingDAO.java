package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
public class TrainingDAO {
    private static final Map<Integer, Training> trainingMap = new HashMap<>();
    private int trainingIdCounter = 0;
    private static final Logger logger = LoggerFactory.getLogger(TrainingDAO.class);

    public void addTraining(Training training) {
        training.setId(trainingIdCounter++);
        trainingMap.put(training.getId(), training);
        logger.info("Training added successfully. Training ID: {}", training.getId());
    }

    public Training getTrainingById(int trainingId) {
        Training training = trainingMap.get(trainingId);
        if (training != null) {
            logger.info("Training found. Training ID: {}", training.getId());
        } else {
            logger.warn("Training with ID {} not found.", trainingId);
        }
        return training;
    }
}
