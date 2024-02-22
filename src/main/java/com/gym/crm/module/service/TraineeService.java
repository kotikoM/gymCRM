package com.gym.crm.module.service;

import com.gym.crm.module.domain.Trainee;

public interface TraineeService {
    public Trainee createTrainee(Trainee trainee);
    public boolean authorize(String userName, String password);
    public Trainee getTraineeByUserName(String userName, String password);
    public Trainee getTraineeById(Integer id, String userName, String password);
    public void updatePassword(String userName, String oldPassword, String newPassword);
    public void updateIsActive(String userName, String password, boolean isActive);

}
