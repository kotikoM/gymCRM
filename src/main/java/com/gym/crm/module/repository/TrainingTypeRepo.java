package com.gym.crm.module.repository;

import com.gym.crm.module.entity.Training;
import com.gym.crm.module.entity.TrainingType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingTypeRepo {
    @Autowired
    private SessionFactory sessionFactory;

    public List<TrainingType> getAllTrainingTypes() {
        return sessionFactory.openSession()
                .createQuery("SELECT t FROM TrainingType t", TrainingType.class)
                .getResultList();
    }
}
