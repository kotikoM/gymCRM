package com.gym.crm.module.service.impl;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.entity.Trainee;
import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.entity.User;
import com.gym.crm.module.repository.RepositoryManager;
import com.gym.crm.module.service.TrainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private RepositoryManager repositoryManager;


    public Trainer createTrainer(Trainer trainer) {
        log.info("Creating new trainer: {}", trainer);
        return repositoryManager.trainerRepo.createTrainer(trainer);
    }

    public RegistrationResponseDTO registerTrainer(String firstName, String lastName, Integer trainingTypeId) {
        return repositoryManager.trainerRepo.registerTrainer(firstName, lastName, trainingTypeId);
    }


    public List<Trainer> getAllTrainers(String userName) {
        log.info("Getting all trainers");
        return repositoryManager.trainerRepo.getAllTrainers();
    }


    public Trainer getTrainerById(Integer id, String userName, String password) {
        if (authorize(userName, password)) {
            log.info("Getting trainer by ID: {}", id);
            return repositoryManager.trainerRepo.getTrainerById(id);
        } else {
            log.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }


    public Trainer getTrainerByUserName(String userName, String password) {
        if (authorize(userName, password)) {
            log.info("Getting trainer by username: {}", userName);
            return repositoryManager.trainerRepo.getTrainerByUserName(userName);
        } else {
            log.warn("Unauthorized access. User: {}", userName);
            return null;
        }
    }


    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        log.info("Getting unassigned trainers for trainee: {}", traineeUsername);
        return repositoryManager.trainerRepo.getUnassignedTrainers(traineeUsername);
    }

    public Map<String, Object> getTrainerProfile(String username) {
        Map<String, Object> response = new HashMap<>();
        User user = repositoryManager.trainerRepo.getUserProfile(username);
        response.put("profile", user);
        List<Trainee> trainees = repositoryManager.traineeRepo.getTrainerTrainees(username);
        response.put("trainees", trainees);
        return response;
    }


    public void updateTrainer(String userName, String firstName, String lastName, Integer specialization, Boolean isActive) {
        User user = repositoryManager.trainerRepo.getUserProfile(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIsActive(isActive);
        Trainer trainer = repositoryManager.trainerRepo.getTrainerByUserName(userName);
        trainer.setTrainingTypeId(specialization);
        repositoryManager.trainerRepo.updateUser(user);
        repositoryManager.trainerRepo.updateTrainer(trainer);
    }


    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            log.info("Updating password for user: {}", userName);
            repositoryManager.trainerRepo.updatePassword(userName, newPassword);
        } else {
            log.warn("Unauthorized access. User: {}", userName);
        }
    }


    public void updateIsActive(String userName, Boolean isActive) {
        log.info("Updating isActive status for user: {}", userName);
        repositoryManager.trainerRepo.updateIsActive(userName, isActive);
    }

    public Boolean authorize(String userName, String password) {
        log.info("Authorizing user with username: {}", userName);
        return repositoryManager.trainerRepo.authorize(userName, password);
    }
}
