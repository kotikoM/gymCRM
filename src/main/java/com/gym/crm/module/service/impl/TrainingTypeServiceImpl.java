package com.gym.crm.module.service.impl;


import com.gym.crm.module.entity.TrainingType;
import com.gym.crm.module.repository.RepositoryManager;
import com.gym.crm.module.service.TrainingTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);
    @Autowired
    private RepositoryManager repositoryManager;

    public List<TrainingType> getAllTrainingTypes() {
        logger.info("Fetching all training types...");
        return repositoryManager.trainingTypeRepo.getAllTrainingTypes();
    }
}
