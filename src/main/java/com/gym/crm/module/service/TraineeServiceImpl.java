package com.gym.crm.module.service;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.domain.User;
import com.gym.crm.module.repository.TraineeRepo;
import com.gym.crm.module.repository.TrainerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TraineeServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    @Autowired
    private TraineeRepo traineeRepo;

    @Autowired
    private TrainerRepo trainerRepo;


    public Trainee createTrainee(Trainee trainee) {
        return traineeRepo.createTrainee(trainee);
    }

    public RegistrationResponseDTO registerTrainee(String firstName, String lastName, Date dateOfBirth, String address) {
        return traineeRepo.registerTrainee(firstName, lastName, dateOfBirth, address);
    }

    public List<Trainee> getAllTrainees(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting all trainees");
            return traineeRepo.getAllTrainees();
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    public Trainee getTraineeById(Integer id, String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by ID: {}", id);
            return traineeRepo.getTraineeById(id);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }


    public Trainee getTraineeByUserName(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by username: {}", userName);
            return traineeRepo.getTraineeByUserName(userName);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    public Map<String, Object> getTraineeProfile(String username) {
        Map<String, Object> response = new HashMap<>();

        User user = traineeRepo.getUserProfile(username);
        response.put("profile", user);
        List<Trainer> trainers = trainerRepo.getTraineeTrainers(username);
        response.put("trainers", trainers);
        return response;
    }

    public void updateTrainee(String userName, String firstName, String lastName, Date dateOfBirth, String address, Boolean isActive) {
        User user = traineeRepo.getUserByUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIsActive(isActive);
        Trainee trainee = traineeRepo.getTraineeByUserName(userName);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        traineeRepo.updateUser(user);
        traineeRepo.updateTrainee(trainee);
    }

    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            logger.info("Updating password for user: {}", userName);
            traineeRepo.updatePassword(userName, newPassword);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    public void updateIsActive(String userName, String password, boolean isActive) {
        if (authorize(userName, password)) {
            logger.info("Updating isActive status for user: {}", userName);
            traineeRepo.updateIsActive(userName, isActive);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    public void deleteTrainee(String userName) {
        traineeRepo.deleteTrainee(userName);
    }

    public boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        return traineeRepo.authorize(userName, password);
    }
}
