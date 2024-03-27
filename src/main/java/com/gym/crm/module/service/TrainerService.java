package com.gym.crm.module.service;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.DTO.TrainerProfileDTO;
import com.gym.crm.module.entity.Trainer;

import java.util.List;
import java.util.Map;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    RegistrationResponseDTO registerTrainer(String firstName, String lastName, Integer trainingTypeId);
    List<Trainer> getAllTrainers(String userName);
    Trainer getTrainerById(Integer id, String userName);
    Trainer getTrainerByUserName(String userName);
    List<Trainer> getUnassignedTrainers(String traineeUsername);
    TrainerProfileDTO getTrainerProfile(String username);
    void updateTrainer(String userName, String firstName, String lastName, Integer specialization, Boolean isActive);
    void updatePassword(String userName, String oldPassword, String newPassword);
    void updateIsActive(String userName, Boolean isActive);
    Boolean authorize(String userName, String password);

}
