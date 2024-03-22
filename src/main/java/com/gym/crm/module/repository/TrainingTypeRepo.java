package com.gym.crm.module.repository;

import com.gym.crm.module.entity.TrainingType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TrainingTypeRepo {
    @Autowired
    private SessionFactory sessionFactory;

    public List<TrainingType> getAllTrainingTypes() {
        log.info("Fetching all training types...");
        return sessionFactory.openSession()
                .createQuery("SELECT t FROM TrainingType t", TrainingType.class)
                .getResultList();
    }
}
