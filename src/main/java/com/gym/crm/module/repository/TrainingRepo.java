package com.gym.crm.module.repository;

import com.gym.crm.module.entity.Training;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class TrainingRepo {
    @Autowired
    private SessionFactory sessionFactory;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Training> getAllTrainings() {
        log.info("Getting all trainings");
        return sessionFactory.openSession()
                .createQuery("SELECT t FROM Training t", Training.class)
                .getResultList();
    }

    public Training getTrainingById(Integer trainingId) {
        log.info("Getting training by ID: {}", trainingId);
        return sessionFactory.openSession()
                .get(Training.class, trainingId);
    }
    public List<Training> getTraineeTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainee trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                    userName, fromDate, toDate, trainerName, trainingTypeId);
            StringBuilder queryBuilder = new StringBuilder("SELECT t FROM Training t WHERE t.traineeId = " +
                    "(SELECT tr.id FROM Trainee tr INNER JOIN User u ON tr.userId=u.id WHERE u.userName = :userName)");

            if (fromDate != null) {
                queryBuilder.append(" AND t.date >= :fromDate");
            }
            if (toDate != null) {
                queryBuilder.append(" AND t.date <= :toDate");
            }
            if (trainerName != null) {
                queryBuilder.append(" AND t.trainerId IN (SELECT tr.id FROM Trainer tr INNER JOIN User u ON tr.userId=u.id WHERE u.userName = :trainerName)");
            }
            if (trainingTypeId != null) {
                queryBuilder.append(" AND t.trainingTypeId = :trainingTypeId");
            }

            TypedQuery<Training> query = session.createQuery(queryBuilder.toString(), Training.class);
            query.setParameter("userName", userName);
            if (fromDate != null) {
                query.setParameter("fromDate", fromDate);
            }
            if (toDate != null) {
                query.setParameter("toDate", toDate);
            }
            if (trainerName != null) {
                query.setParameter("trainerName", trainerName);
            }
            if (trainingTypeId != null) {
                query.setParameter("trainingTypeId", trainingTypeId);
            }

            List<Training> trainings = query.getResultList();
            log.info("Retrieved {} trainee trainings", trainings.size());
            return trainings;
        }
    }

    public List<Training> getTrainerTrainingsByCriteria(String userName, Date fromDate, Date toDate, String trainerName, Integer trainingTypeId) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainer trainings by criteria - UserName: {}, FromDate: {}, ToDate: {}, TrainerName: {}, TrainingTypeId: {}",
                    userName, fromDate, toDate, trainerName, trainingTypeId);
            StringBuilder queryBuilder = new StringBuilder("SELECT t FROM Training t WHERE t.trainerId = " +
                    "(SELECT tr.id FROM trainer tr INNER JOIN User u ON tr.userId=u.id WHERE u.userName = :userName)");

            if (fromDate != null) {
                queryBuilder.append(" AND t.date >= :fromDate");
            }
            if (toDate != null) {
                queryBuilder.append(" AND t.date <= :toDate");
            }
            if (trainerName != null) {
                queryBuilder.append(" AND t.trainerId IN (SELECT tr.id FROM Trainer tr INNER JOIN User u ON tr.userId=u.id WHERE u.userName = :trainerName)");
            }
            if (trainingTypeId != null) {
                queryBuilder.append(" AND t.trainingTypeId = :trainingTypeId");
            }

            TypedQuery<Training> query = session.createQuery(queryBuilder.toString(), Training.class);
            query.setParameter("userName", userName);
            if (fromDate != null) {
                query.setParameter("fromDate", fromDate);
            }
            if (toDate != null) {
                query.setParameter("toDate", toDate);
            }
            if (trainerName != null) {
                query.setParameter("trainerName", trainerName);
            }
            if (trainingTypeId != null) {
                query.setParameter("trainingTypeId", trainingTypeId);
            }

            List<Training> trainings = query.getResultList();
            log.info("Retrieved {} trainer trainings", trainings.size());
            return trainings;
        }

    }

    public Training createTrainer(Training training) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Creating new training: {}", training);
            session.beginTransaction();
            Integer trainingId = (Integer) session.save(training);
            session.getTransaction().commit();
            log.info("Training created successfully with ID: {}", trainingId);
            return getTrainingById(trainingId);
        }
    }
}

