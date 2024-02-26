package com.gym.crm.module.repository;

import com.gym.crm.module.domain.User;
import com.gym.crm.module.domain.Trainee;
import jakarta.persistence.TypedQuery;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraineeRepo extends com.gym.crm.module.repository.Repository {

    public List<Trainee> getAllTrainees() {
        return sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainee t", Trainee.class)
                .getResultList();
    }

    public Trainee getTraineeById(Integer traineeId) {
        return sessionFactory.openSession()
                .get(Trainee.class, traineeId);
    }

    public Trainee getTraineeByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Trainee> query = session.createQuery(
                    "SELECT t FROM Trainee t WHERE t.userId = " +
                            "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                    Trainee.class);
            query.setParameter("userName", userName);

            return query.getSingleResult();
        }
    }

    public Trainee createTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Integer traineeId = (Integer) session.save(trainee);
            session.getTransaction().commit();
            return getTraineeById(traineeId);
        }
    }

    public void updateTrainee(Trainee trainee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(trainee);
            session.getTransaction().commit();
        }
    }

    public void deleteTrainee(String userName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Trainee trainee = getTraineeByUserName(userName);
            if (trainee != null) {
                trainee = session.get(Trainee.class, trainee.getId());
                session.delete(trainee);
            }
            session.getTransaction().commit();
        }
    }

    public boolean authorize(String userName, String password) {
        Trainee trainee = getTraineeByUserName(userName);
        return trainee != null && checkPassword(userName, password);
    }
}
