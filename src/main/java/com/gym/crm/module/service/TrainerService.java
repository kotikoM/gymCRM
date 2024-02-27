package com.gym.crm.module.service;

import com.gym.crm.module.domain.Trainer;

import java.util.List;

public interface TrainerService {
    public Trainer createTrainer(Trainer trainer);
    public boolean authorize(String userName, String password);
    public List<Trainer> getAllTrainers(String userName, String password);
    public Trainer getTrainerById(Integer id, String userName, String password);
    public Trainer getTrainerByUserName(String userName, String password);
    public List<Trainer> getUnassignedTrainers(String traineeUsername, String password);
    public void updateTrainer(String userName, String password, Trainer trainer);
    public void updatePassword(String userName, String oldPassword, String newPassword);
    public void updateIsActive(String userName, String password, boolean isActive);
}
