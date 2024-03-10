package com.gym.crm.module.service.impl;

import com.gym.crm.module.entity.Training;
import com.gym.crm.module.repository.RepositoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainingServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);


    @Autowired
    private RepositoryManager repositoryManager;


    public Training getTrainingById(Integer trainingId) {
        logger.info("Getting training by ID: {}", trainingId);
        return repositoryManager.trainingRepo.getTrainingById(trainingId);
    }

    public List<Training> getAllTrainings() {
        return repositoryManager.trainingRepo.getAllTrainings();
    }


    public List<Training> getTraineeTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        logger.info("Getting trainee trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                userName, fromDate, toDate, trainerName, trainingTypeId);
        return repositoryManager.trainingRepo.getTraineeTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
    }


    public List<Training> getTrainerTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        logger.info("Getting trainer trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                userName, fromDate, toDate, trainerName, trainingTypeId);
        return repositoryManager.trainingRepo.getTrainerTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
    }

    public Training createTraining(Training training) {
        logger.info("Creating new training: {}", training);
        return repositoryManager.trainingRepo.createTrainer(training);
    }
}
