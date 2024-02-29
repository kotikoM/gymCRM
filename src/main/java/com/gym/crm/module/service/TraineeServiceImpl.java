package com.gym.crm.module.service;

import com.gym.crm.module.DTO.TraineeRegistrationResponseDTO;
import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.repository.TraineeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    @Autowired
    private TraineeRepo traineeRepo;

    @Override
    public Trainee createTrainee(Trainee trainee) {
        return traineeRepo.createTrainee(trainee);
    }

    public TraineeRegistrationResponseDTO createTrainee(String firstName, String lastName, Date dateOfBirth, String address) {
        return traineeRepo.createTrainee(firstName, lastName, dateOfBirth, address);
    }

    @Override
    public boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        return traineeRepo.authorize(userName, password);
    }

    @Override
    public List<Trainee> getAllTrainees(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting all trainees");
            return traineeRepo.getAllTrainees();
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public Trainee getTraineeById(Integer id, String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by ID: {}", id);
            return traineeRepo.getTraineeById(id);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public Trainee getTraineeByUserName(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by username: {}", userName);
            return traineeRepo.getTraineeByUserName(userName);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    @Override
    public void updateTrainee(String userName, String password, Trainee trainee) {
        if (authorize(userName, password)) {
            logger.info("Updating trainee: {}", trainee);
            traineeRepo.updateTrainee(trainee);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    @Override
    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            logger.info("Updating password for user: {}", userName);
            traineeRepo.updatePassword(userName, newPassword);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    @Override
    public void updateIsActive(String userName, String password, boolean isActive) {
        if (authorize(userName, password)) {
            logger.info("Updating isActive status for user: {}", userName);
            traineeRepo.updateIsActive(userName, isActive);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }
}
