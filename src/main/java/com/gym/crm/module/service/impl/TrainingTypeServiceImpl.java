package com.gym.crm.module.service.impl;


import com.gym.crm.module.entity.TrainingType;
import com.gym.crm.module.repository.RepositoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeServiceImpl {
    @Autowired
    private RepositoryManager repositoryManager;

    public List<TrainingType> getAllTrainingTypes() {
        return repositoryManager.trainingTypeRepo.getAllTrainingTypes();
    }
}
