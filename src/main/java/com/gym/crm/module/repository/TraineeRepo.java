package com.gym.crm.module.repository;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.entity.Trainee;
import com.gym.crm.module.entity.User;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class TraineeRepo extends UserRepo {

    public List<Trainee> getAllTrainees() {
        log.info("Getting all trainees");
        List<Trainee> trainees = sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainee t", Trainee.class)
                .getResultList();
        log.info("Retrieved {} trainees", trainees.size());
        return trainees;
    }

    public Trainee getTraineeById(Integer traineeId) {
        log.info("Getting trainee by ID: {}", traineeId);
        Trainee trainee = sessionFactory.openSession()
                .get(Trainee.class, traineeId);
        log.info("Retrieved trainee: {}", trainee);
        return trainee;
    }

    public Trainee getTraineeByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainee by username: {}", userName);
            TypedQuery<Trainee> query = session.createQuery(
                    "SELECT t FROM Trainee t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainee.class);
            query.setParameter("userName", userName);
            Trainee trainee = query.getSingleResult();
            log.info("Retrieved trainee: {}", trainee);
            return trainee;
        }
    }

    public List<Trainee> getTrainerTrainees(String username) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainer trainees: {}", username);
            String hql = "SELECT tra FROM Trainee tra WHERE tra.id IN " +
                    "(SELECT t.traineeId FROM Training t WHERE t.trainerId = " +
                    "(SELECT tr.id FROM Trainer tr WHERE tr.userId = " +
                    "(SELECT u.id FROM User u WHERE u.userName = :username)))";

            TypedQuery<Trainee> query = session.createQuery(hql, Trainee.class);
            query.setParameter("username", username);

            List<Trainee> trainees = query.getResultList();
            log.info("Retrieved {} trainers trainees", trainees.size());
            return trainees;
        }
    }

    public Trainee createTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Creating new trainee: {}", trainee);
            session.beginTransaction();
            Integer traineeId = (Integer) session.save(trainee);
            session.getTransaction().commit();
            log.info("Trainee created successfully with ID: {}", traineeId);
            return getTraineeById(traineeId);
        }
    }

    public RegistrationResponseDTO registerTrainee(String firstName, String lastName, Date dateOfBirth, String address) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = createUser(firstName, lastName);
            Trainee trainee = new Trainee(dateOfBirth, address, user.getId());
            session.save(trainee);
            session.getTransaction().commit();
            return new RegistrationResponseDTO(user.getUserName(), user.getPassword());
        }
    }

    public void updateTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Updating trainee: {}", trainee);
            session.beginTransaction();
            session.update(trainee);
            session.getTransaction().commit();
            log.info("Trainee updated successfully");
        }
    }

    public void deleteTrainee(String userName) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Deleting trainee by username: {}", userName);
            session.beginTransaction();
            Trainee trainee = getTraineeByUserName(userName);
            if (trainee != null) {
                trainee = session.get(Trainee.class, trainee.getId());
                session.delete(trainee);
            }
            session.getTransaction().commit();
            log.info("Trainee deleted successfully");
        }
    }

    public boolean authorize(String userName, String password) {
        log.info("Authorizing user with username: {}", userName);
        Trainee trainee = getTraineeByUserName(userName);
        boolean isAuthorized = trainee != null && checkPassword(userName, password);
        log.info("Authorization result: {}", isAuthorized);
        return isAuthorized;
    }

}
