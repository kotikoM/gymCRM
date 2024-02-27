package com.gym.crm.module.service;

import com.gym.crm.module.domain.Training;
import com.gym.crm.module.repository.TrainingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    @Autowired
    private TrainingRepo trainingRepo;

    @Override
    public Training getTrainingById(Integer trainingId) {
        logger.info("Getting training by ID: {}", trainingId);
        return trainingRepo.getTrainingById(trainingId);
    }

    @Override
    public List<Training> getTraineeTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        logger.info("Getting trainee trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                userName, fromDate, toDate, trainerName, trainingTypeId);
        return trainingRepo.getTraineeTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
    }

    @Override
    public List<Training> getTrainerTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        logger.info("Getting trainer trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                userName, fromDate, toDate, trainerName, trainingTypeId);
        return trainingRepo.getTrainerTrainingsByCriteria(userName, fromDate, toDate, trainerName, trainingTypeId);
    }

    @Override
    public Training createTrainer(Training training) {
        logger.info("Creating new training: {}", training);
        return trainingRepo.createTrainer(training);
    }
}
