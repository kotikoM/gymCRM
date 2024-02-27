package com.gym.crm.module.service;

import com.gym.crm.module.domain.Training;

import java.util.Date;
import java.util.List;


public interface TrainingService {
    public Training getTrainingById(Integer trainingId);

    public List<Training> getTraineeTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId);

    public List<Training> getTrainerTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId);

    public Training createTrainer(Training training);
}
