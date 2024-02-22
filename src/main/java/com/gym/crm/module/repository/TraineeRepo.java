package com.gym.crm.module.repository;

import com.gym.crm.module.domain.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.crm.module.domain.Trainee;

import java.util.List;

@Repository
public class TraineeRepo {
    @Autowired
    private SessionFactory sessionFactory;

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

    public void updatePassword(String userName, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            User user = query.getSingleResult();
            user.setPassword(newPassword);
            session.getTransaction().commit();
        }
    }

    public void updateIsActive(String userName, boolean isActive) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);

            User user = query.getSingleResult();
            user.setIsActive(isActive);
            session.getTransaction().commit();
        }
    }
    public boolean authorize(String userName, String password) {
        Trainee trainee = getTraineeByUserName(userName);
        return trainee != null && checkPassword(userName, password);
    }

    public boolean checkPassword(String userName, String password) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            return query.getSingleResult().getPassword().equals(password);
        }
    }
}
