package com.gym.crm.module.service;

import com.gym.crm.module.dao.TraineeDAO;
import com.gym.crm.module.domain.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TraineeService {
    @Autowired
    private TraineeDAO traineeDAO;
    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    public void addTrainee(Date dateOfBirth, String address, int userId) {
        logger.info("Adding a new trainee...");
        Trainee trainee = new Trainee(dateOfBirth, address, userId);
        traineeDAO.addTrainee(trainee);
        logger.info("Trainee added successfully. Trainee ID: {}", trainee.getId());
    }

    public void updateTrainee(Trainee trainee) {
        logger.info("Updating trainee with ID: {}", trainee.getId());
        traineeDAO.updateTrainee(trainee);
        logger.info("Trainee updated successfully.");
    }

    public void deleteTrainee(int traineeId) {
        logger.info("Deleting trainee with ID: {}", traineeId);
        traineeDAO.deleteTrainee(traineeId);
        logger.info("Trainee deleted successfully.");
    }

    public Trainee getTraineeById(int traineeId) {
        logger.info("Fetching trainee with ID: {}", traineeId);
        Trainee trainee = traineeDAO.getTraineeById(traineeId);
        if (trainee != null) {
            logger.info("Trainee found: {}", trainee);
        } else {
            logger.warn("Trainee with ID {} not found.", traineeId);
        }
        return trainee;
    }

    public void clear(){
        this.traineeDAO.clear();
    }
}
