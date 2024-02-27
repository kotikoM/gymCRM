package com.gym.crm.module.repository;

import com.gym.crm.module.domain.Trainee;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraineeRepo extends com.gym.crm.module.repository.Repository {

    private static final Logger logger = LoggerFactory.getLogger(TraineeRepo.class);

    public List<Trainee> getAllTrainees() {
        logger.info("Getting all trainees");
        List<Trainee> trainees = sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainee t", Trainee.class)
                .getResultList();
        logger.info("Retrieved {} trainees", trainees.size());
        return trainees;
    }

    public Trainee getTraineeById(Integer traineeId) {
        logger.info("Getting trainee by ID: {}", traineeId);
        Trainee trainee = sessionFactory.openSession()
                .get(Trainee.class, traineeId);
        logger.info("Retrieved trainee: {}", trainee);
        return trainee;
    }

    public Trainee getTraineeByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Getting trainee by username: {}", userName);
            TypedQuery<Trainee> query = session.createQuery(
                    "SELECT t FROM Trainee t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainee.class);
            query.setParameter("userName", userName);
            Trainee trainee = query.getSingleResult();
            logger.info("Retrieved trainee: {}", trainee);
            return trainee;
        }
    }

    public Trainee createTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Creating new trainee: {}", trainee);
            session.beginTransaction();
            Integer traineeId = (Integer) session.save(trainee);
            session.getTransaction().commit();
            logger.info("Trainee created successfully with ID: {}", traineeId);
            return getTraineeById(traineeId);
        }
    }

    public void updateTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Updating trainee: {}", trainee);
            session.beginTransaction();
            session.update(trainee);
            session.getTransaction().commit();
            logger.info("Trainee updated successfully");
        }
    }

    public void deleteTrainee(String userName) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Deleting trainee by username: {}", userName);
            session.beginTransaction();
            Trainee trainee = getTraineeByUserName(userName);
            if (trainee != null) {
                trainee = session.get(Trainee.class, trainee.getId());
                session.delete(trainee);
            }
            session.getTransaction().commit();
            logger.info("Trainee deleted successfully");
        }
    }

    public boolean authorize(String userName, String password) {
        logger.info("Authorizing user with username: {}", userName);
        Trainee trainee = getTraineeByUserName(userName);
        boolean isAuthorized = trainee != null && checkPassword(userName, password);
        logger.info("Authorization result: {}", isAuthorized);
        return isAuthorized;
    }
}
