package com.gym.crm.module.service.impl;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.entity.Trainee;
import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.entity.User;
import com.gym.crm.module.repository.RepositoryManager;
import com.gym.crm.module.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TraineeServiceImpl implements TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);
    
    @Autowired
    private RepositoryManager repositoryManager;


    public Trainee createTrainee(Trainee trainee) {
        logger.info("Creating trainee...");
        return repositoryManager.traineeRepo.createTrainee(trainee);
    }

    public RegistrationResponseDTO registerTrainee(String firstName, String lastName, Date dateOfBirth, String address) {
        logger.info("Registering trainee...");
        return repositoryManager.traineeRepo.registerTrainee(firstName, lastName, dateOfBirth, address);
    }

    public List<Trainee> getAllTrainees(String userName) {
        logger.info("Getting all trainees");
        return repositoryManager.traineeRepo.getAllTrainees();
    }

    public Trainee getTraineeById(Integer id, String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by ID: {}", id);
            return repositoryManager.traineeRepo.getTraineeById(id);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }


    public Trainee getTraineeByUserName(String userName, String password) {
        if (authorize(userName, password)) {
            logger.info("Getting trainee by username: {}", userName);
            return repositoryManager.traineeRepo.getTraineeByUserName(userName);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }

    public Map<String, Object> getTraineeProfile(String username) {
        Map<String, Object> response = new HashMap<>();
        User user = repositoryManager.traineeRepo.getUserProfile(username);
        response.put("profile", user);
        List<Trainer> trainers = repositoryManager.trainerRepo.getTraineeTrainers(username);
        response.put("trainers", trainers);
        logger.info("Trainee profile information fetched");
        return response;
    }
    public void updateTrainee(String userName, String firstName, String lastName, Date dateOfBirth, String address, Boolean isActive) {
        User user = repositoryManager.traineeRepo.getUserByUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIsActive(isActive);
        Trainee trainee = repositoryManager.traineeRepo.getTraineeByUserName(userName);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        repositoryManager.traineeRepo.updateUser(user);
        repositoryManager.traineeRepo.updateTrainee(trainee);
        logger.info("Trainee profile updated");
    }

    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            logger.info("Updating password for user: {}", userName);
            repositoryManager.traineeRepo.updatePassword(userName, newPassword);
        } else {
            logger.warn("Unauthorized access. User: {}", userName);
        }
    }

    public void updateIsActive(String userName, Boolean isActive) {
        logger.info("Updating isActive status for user: {}", userName);
        repositoryManager.traineeRepo.updateIsActive(userName, isActive);
    }

    public void deleteTrainee(String userName) {
        repositoryManager.traineeRepo.deleteTrainee(userName);
    }

    public Boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        return repositoryManager.traineeRepo.authorize(userName, password);
    }
}
