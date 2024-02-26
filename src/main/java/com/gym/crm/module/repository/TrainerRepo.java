package com.gym.crm.module.repository;

import com.gym.crm.module.domain.Trainer;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerRepo extends com.gym.crm.module.repository.Repository {

    public List<Trainer> getAllTrainers() {
        return sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainer t", Trainer.class)
                .getResultList();
    }

    public Trainer getTrainerById(Integer trainerId) {
        return sessionFactory.openSession()
                .get(Trainer.class, trainerId);
    }

    public Trainer getTrainerByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Trainer> query = session.createQuery(
                    "SELECT t FROM Trainer t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainer.class);
            query.setParameter("userName", userName);

            return query.getSingleResult();
        }
    }

    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT tra FROM Trainer tra WHERE tra.id NOT IN " +
                            "(SELECT t.trainerId FROM Training t WHERE t.traineeId = " +
                                "(SELECT tr.id FROM Trainee tr WHERE tr.userId = " +
                                    "(SELECT u.id FROM User u WHERE u.userName = :traineeUsername)))";

            TypedQuery<Trainer> query = session.createQuery(hql, Trainer.class);
            query.setParameter("traineeUsername", traineeUsername);

            return query.getResultList();
        }
    }

    public Trainer createTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Integer trainerId = (Integer) session.save(trainer);
            session.getTransaction().commit();
            return getTrainerById(trainerId);
        }
    }

    public void updateTrainer(Trainer trainer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(trainer);
            session.getTransaction().commit();
        }
    }

    public boolean authorize(String userName, String password) {
        Trainer trainer = getTrainerByUserName(userName);
        return trainer != null && checkPassword(userName, password);
    }
}
