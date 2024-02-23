package com.gym.crm.module.service;

import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerRepo trainerRepo;
    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepo.createTrainer(trainer);
    }

    @Override
    public boolean authorize(String userName, String password) {
        return trainerRepo.authorize(userName, password);
    }

    @Override
    public List<Trainer> getAllTrainers(String userName, String password) {
        return authorize(userName, password) ?
                trainerRepo.getAllTrainers() : null;
    }

    @Override
    public Trainer getTrainerById(Integer id, String userName, String password) {
        return authorize(userName, password) ?
                trainerRepo.getTrainerById(id) : null;
    }

    @Override
    public Trainer getTrainerByUserName(String userName, String password) {
        return authorize(userName, password) ?
                trainerRepo.getTrainerByUserName(userName) : null;
    }

    @Override
    public void updateTrainer(String userName, String password, Trainer trainer) {
        if (authorize(userName, password)) {
            trainerRepo.updateTrainer(trainer);
        }
    }

    @Override
    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            trainerRepo.updatePassword(userName, newPassword);
        }
    }

    @Override
    public void updateIsActive(String userName, String password, boolean isActive) {
        if (authorize(userName, password)) {
            trainerRepo.updateIsActive(userName, isActive);
        }
    }
}
