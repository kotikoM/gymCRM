package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TraineeDAO {
    private final Map<Integer, Trainee> traineeMap = new HashMap<>();
    private int traineeIdCounter = 0;

    private static final Logger logger = LoggerFactory.getLogger(TraineeDAO.class);

    public void addTrainee(Trainee trainee) {
        trainee.setId(traineeIdCounter++);
        traineeMap.put(trainee.getId(), trainee);
        logger.info("Trainee added successfully. Trainee ID: {}", trainee.getId());
    }

    public void updateTrainee(Trainee trainee) {
        if (traineeMap.containsKey(trainee.getId())) {
            traineeMap.put(trainee.getId(), trainee);
            logger.info("Trainee updated successfully. Trainee ID: {}", trainee.getId());
        } else {
            logger.warn("Unable to update trainee. Trainee with ID {} not found.", trainee.getId());
            // Optionally, you can throw an exception or handle the case as needed.
        }
    }

    public void deleteTrainee(int traineeID) {
        if (traineeMap.containsKey(traineeID)) {
            traineeMap.remove(traineeID);
            logger.info("Trainee deleted successfully. Trainee ID: {}", traineeID);
        } else {
            logger.warn("Unable to delete trainee. Trainee with ID {} not found.", traineeID);
            // Optionally, you can throw an exception or handle the case as needed.
        }
    }

    public Trainee getTraineeById(int traineeId) {
        Trainee trainee = traineeMap.get(traineeId);
        if (trainee != null) {
            logger.info("Trainee found. Trainee ID: {}", trainee.getId());
        } else {
            logger.warn("Trainee with ID {} not found.", traineeId);
        }
        return trainee;
    }
}
