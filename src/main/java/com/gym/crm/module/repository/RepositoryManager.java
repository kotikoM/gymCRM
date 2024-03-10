package com.gym.crm.module.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryManager {
    @Autowired
    public TraineeRepo traineeRepo;
    @Autowired
    public TrainerRepo trainerRepo;
    @Autowired
    public TrainingRepo trainingRepo;
    @Autowired
    public TrainingTypeRepo trainingTypeRepo;
    @Autowired
    public UserRepo userRepo;
}
