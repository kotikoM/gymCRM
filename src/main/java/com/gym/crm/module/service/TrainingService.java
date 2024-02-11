package com.gym.crm.module.service;

import com.gym.crm.module.dao.TrainingDAO;
import com.gym.crm.module.domain.Training;
import com.gym.crm.module.domain.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TrainingService {
    @Autowired
    private TrainingDAO trainingDAO;
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

    public void addTraining(int traineeId, int trainerId, String trainingName,
                            TrainingType trainingType, Date trainingDate, Number trainingDuration) {
        logger.info("Adding a new training record...");
        Training training = new Training(traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);
        trainingDAO.addTraining(training);
        logger.info("Training added successfully. Training ID: {}", training.getId());
    }

    public Training getTrainingById(int trainingId) {
        logger.info("Fetching training with ID: {}", trainingId);
        Training training = trainingDAO.getTrainingById(trainingId);
        if (training != null) {
            logger.info("Training found: {}", training);
        } else {
            logger.warn("Training with ID {} not found", trainingId);
        }
        return training;
    }
}
