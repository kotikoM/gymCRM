package com.gym.crm.module.repository;

import com.gym.crm.module.DTO.RegistrationResponseDTO;
import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.entity.User;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TrainerRepo extends UserRepo {

    public List<Trainer> getAllTrainers() {
        log.info("Getting all trainers");
        List<Trainer> trainers = sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainer t", Trainer.class)
                .getResultList();
        log.info("Retrieved {} trainers", trainers.size());
        return trainers;
    }

    public Trainer getTrainerById(Integer trainerId) {
        log.info("Getting trainer by ID: {}", trainerId);
        Trainer trainer = sessionFactory.openSession()
                .get(Trainer.class, trainerId);
        log.info("Retrieved trainer: {}", trainer);
        return trainer;
    }

    public Trainer getTrainerByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainer by username: {}", userName);
            TypedQuery<Trainer> query = session.createQuery(
                    "SELECT t FROM Trainer t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainer.class);
            query.setParameter("userName", userName);
            Trainer trainer = query.getSingleResult();
            log.info("Retrieved trainer: {}", trainer);
            return trainer;
        }
    }

    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting unassigned trainers for trainee: {}", traineeUsername);
            String hql = "SELECT tra FROM Trainer tra WHERE tra.id NOT IN " +
                    "(SELECT t.trainerId FROM Training t WHERE t.traineeId = " +
                    "(SELECT tr.id FROM Trainee tr WHERE tr.userId = " +
                    "(SELECT u.id FROM User u WHERE u.userName = :traineeUsername)))";

            TypedQuery<Trainer> query = session.createQuery(hql, Trainer.class);
            query.setParameter("traineeUsername", traineeUsername);

            List<Trainer> unassignedTrainers = query.getResultList();
            log.info("Retrieved {} unassigned trainers", unassignedTrainers.size());
            return unassignedTrainers;
        }
    }

    public List<Trainer> getTraineeTrainers(String username) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Getting trainee trainers: {}", username);
            String hql = "SELECT tra FROM Trainer tra WHERE tra.id IN " +
                    "(SELECT t.trainerId FROM Training t WHERE t.traineeId = " +
                    "(SELECT tr.id FROM Trainee tr WHERE tr.userId = " +
                    "(SELECT u.id FROM User u WHERE u.userName = :username)))";

            TypedQuery<Trainer> query = session.createQuery(hql, Trainer.class);
            query.setParameter("username", username);

            List<Trainer> trainers = query.getResultList();
            log.info("Retrieved {} trainee trainers", trainers.size());
            return trainers;
        }
    }

    public Trainer createTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Creating new trainer: {}", trainer);
            session.beginTransaction();
            Integer trainerId = (Integer) session.save(trainer);
            session.getTransaction().commit();
            log.info("Trainer created successfully with ID: {}", trainerId);
            return getTrainerById(trainerId);
        }
    }

    public RegistrationResponseDTO registerTrainer(String firstName, String lastName, Integer trainingTypeId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = createUser(firstName, lastName);
            Trainer trainer = new Trainer(trainingTypeId, user.getId());
            session.save(trainer);
            session.getTransaction().commit();
            return new RegistrationResponseDTO(user.getUserName(), user.getPassword());
        }
    }

    public void updateTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Updating trainer: {}", trainer);
            session.beginTransaction();
            session.update(trainer);
            session.getTransaction().commit();
            log.info("Trainer updated successfully");
        }
    }

    public boolean authorize(String userName, String password) {
        log.info("Authorizing user with username: {}", userName);
        Trainer trainer = getTrainerByUserName(userName);
        boolean isAuthorized = trainer != null && checkPassword(userName, password);
        log.info("Authorization result: {}", isAuthorized);
        return isAuthorized;
    }
}
