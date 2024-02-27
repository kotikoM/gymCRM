package com.gym.crm.module.repository;

import com.gym.crm.module.domain.Trainer;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerRepo extends com.gym.crm.module.repository.Repository {

    private static final Logger logger = LoggerFactory.getLogger(TrainerRepo.class);

    public List<Trainer> getAllTrainers() {
        logger.info("Getting all trainers");
        List<Trainer> trainers = sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainer t", Trainer.class)
                .getResultList();
        logger.info("Retrieved {} trainers", trainers.size());
        return trainers;
    }

    public Trainer getTrainerById(Integer trainerId) {
        logger.info("Getting trainer by ID: {}", trainerId);
        Trainer trainer = sessionFactory.openSession()
                .get(Trainer.class, trainerId);
        logger.info("Retrieved trainer: {}", trainer);
        return trainer;
    }

    public Trainer getTrainerByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Getting trainer by username: {}", userName);
            TypedQuery<Trainer> query = session.createQuery(
                    "SELECT t FROM Trainer t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainer.class);
            query.setParameter("userName", userName);
            Trainer trainer = query.getSingleResult();
            logger.info("Retrieved trainer: {}", trainer);
            return trainer;
        }
    }

    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Getting unassigned trainers for trainee: {}", traineeUsername);
            String hql = "SELECT tra FROM Trainer tra WHERE tra.id NOT IN " +
                    "(SELECT t.trainerId FROM Training t WHERE t.traineeId = " +
                    "(SELECT tr.id FROM Trainee tr WHERE tr.userId = " +
                    "(SELECT u.id FROM User u WHERE u.userName = :traineeUsername)))";

            TypedQuery<Trainer> query = session.createQuery(hql, Trainer.class);
            query.setParameter("traineeUsername", traineeUsername);

            List<Trainer> unassignedTrainers = query.getResultList();
            logger.info("Retrieved {} unassigned trainers", unassignedTrainers.size());
            return unassignedTrainers;
        }
    }

    public Trainer createTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Creating new trainer: {}", trainer);
            session.beginTransaction();
            Integer trainerId = (Integer) session.save(trainer);
            session.getTransaction().commit();
            logger.info("Trainer created successfully with ID: {}", trainerId);
            return getTrainerById(trainerId);
        }
    }

    public void updateTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Updating trainer: {}", trainer);
            session.beginTransaction();
            session.update(trainer);
            session.getTransaction().commit();
            logger.info("Trainer updated successfully");
        }
    }

    public boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        Trainer trainer = getTrainerByUserName(userName);
        boolean isAuthorized = trainer != null && checkPassword(userName, password);
        logger.info("Authorization result: {}", isAuthorized);
        return isAuthorized;
    }
}
