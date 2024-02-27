package com.gym.crm.module.service;

import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.repository.TrainerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerRepo trainerRepo;

    @Override
    public Trainer createTrainer(Trainer trainer) {
        logger.info("Creating new trainer: {}", trainer);
        return trainerRepo.createTrainer(trainer);
    }

    @Override
    public boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        return trainerRepo.authorize(userName, password);
    }

    @Override
    public List<Trainer> getAllTrainers(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting all trainers");
            return trainerRepo.getAllTrainers();
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public Trainer getTrainerById(Integer id, String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainer by ID: {}", id);
            return trainerRepo.getTrainerById(id);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public Trainer getTrainerByUserName(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainer by username: {}", userName);
            return trainerRepo.getTrainerByUserName(userName);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public List<Trainer> getUnassignedTrainers(String traineeUsername, String password) {
        if (authorize(traineeUsername, password)) {
            logger.info("Getting unassigned trainers for trainee: {}", traineeUsername);
            return trainerRepo.getUnassignedTrainers(traineeUsername);
        } else {
            logger.warn("Unauthorized access. User: {}", traineeUsername);
            return null;
        }
    }

    @Override
    public void updateTrainer(String userName, String password, Trainer trainer) {
        if (authorize(userName, password)) {
            logger.info("Updating trainer: {}", trainer);
            trainerRepo.updateTrainer(trainer);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    @Override
    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            logger.info("Updating password for user: {}", userName);
            trainerRepo.updatePassword(userName, newPassword);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    @Override
    public void updateIsActive(String userName, String password, boolean isActive) {
        if (authorize(userName, password)) {
            logger.info("Updating isActive status for user: {}", userName);
            trainerRepo.updateIsActive(userName, isActive);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }
}
