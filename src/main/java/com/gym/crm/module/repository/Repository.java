package com.gym.crm.module.repository;

import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.domain.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Repository
public class Repository {
    @Autowired
    protected SessionFactory sessionFactory;

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
