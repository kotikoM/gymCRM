package com.gym.crm.module.service;

import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.repository.TraineeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService{
    @Autowired
    private TraineeRepo traineeRepo;

    @Override
    public Trainee createTrainee(Trainee trainee) {
        return traineeRepo.createTrainee(trainee);
    }

    @Override
    public boolean authorize(String userName, String password) {
        return traineeRepo.authorize(userName, password);
    }

    @Override
    public List<Trainee> getAllTrainees(String userName, String password) {
        return authorize(userName, password) ?
                traineeRepo.getAllTrainees() : null;
    }

    @Override
    public Trainee getTraineeById(Integer id, String userName, String password) {
        return authorize(userName, password) ?
                traineeRepo.getTraineeById(id) : null;
    }

    @Override
    public Trainee getTraineeByUserName(String userName, String password) {
        return authorize(userName, password) ?
                traineeRepo.getTraineeByUserName(userName) : null;
    }

    @Override
    public void updateTrainee(String userName, String password, Trainee trainee) {
        if (authorize(userName, password)) {
            traineeRepo.updateTrainee(trainee);
        }
    }

    @Override
    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (authorize(userName, oldPassword)) {
            traineeRepo.updatePassword(userName, newPassword);
        }
    }

    @Override
    public void updateIsActive(String userName, String password, boolean isActive) {
        if (authorize(userName, password)) {
            traineeRepo.updateIsActive(userName, isActive);
        }
    }
}
