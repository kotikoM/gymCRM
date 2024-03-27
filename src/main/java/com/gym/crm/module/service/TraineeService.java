package com.gym.crm.module.service;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.DTO.TraineeProfileDTO;
import com.gym.crm.module.entity.Trainee;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);
    RegistrationResponseDTO registerTrainee(String firstName, String lastName, Date dateOfBirth, String address);
    List<Trainee> getAllTrainees(String userName);
    Trainee getTraineeById(Integer id, String userName);
    Trainee getTraineeByUserName(String userName);
    TraineeProfileDTO getTraineeProfile(String username);
    void updateTrainee(String userName, String firstName, String lastName, Date dateOfBirth, String address, Boolean isActive);
    void updatePassword(String userName, String oldPassword, String newPassword);
    void updateIsActive(String userName, Boolean isActive);
    void deleteTrainee(String userName);
    Boolean authorize(String userName, String password);
}
