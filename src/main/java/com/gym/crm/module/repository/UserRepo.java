package com.gym.crm.module.repository;

import com.gym.crm.module.domain.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    private static final Logger logger = LoggerFactory.getLogger(UserRepo.class);

    @Autowired
    protected SessionFactory sessionFactory;

    public User getUserById(Integer userId) {
        return sessionFactory.openSession()
                .get(User.class, userId);
    }

    public User createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Creating new user: {}", user);
            session.beginTransaction();
            Integer userId = (Integer) session.save(user);
            session.getTransaction().commit();
            logger.info("User created successfully with ID: {}", userId);
            return getUserById(userId);
        }
    }

    public User createUser(Integer id, String firstName, String lastName, Boolean isActive) {
        User user = new User(id, firstName, lastName, isActive);
        return createUser(user);
    }

    public User createUser(String firstName, String lastName) {
        return createUser(null, firstName, lastName, true);
    }

    public User getUserProfile(String userName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            User user = query.getSingleResult();
            user.setPassword(null);
            return user;
        }
    }

    public void updatePassword(String userName, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Updating password for user: {}", userName);
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            User user = query.getSingleResult();
            user.setPassword(newPassword);
            session.getTransaction().commit();
            logger.info("Password updated successfully for user: {}", userName);
        } catch (Exception e) {
            logger.error("Error updating password for user: {}", userName, e);
        }
    }

    public void updateIsActive(String userName, boolean isActive) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Updating isActive status for user: {}", userName);
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);

            User user = query.getSingleResult();
            user.setIsActive(isActive);
            session.getTransaction().commit();
            logger.info("isActive status updated successfully for user: {}", userName);
        } catch (Exception e) {
            logger.error("Error updating isActive status for user: {}", userName, e);
        }
    }

    public Boolean checkPassword(String userName, String password) {
        try (Session session = sessionFactory.openSession()) {
            logger.info("Checking password for user: {}", userName);
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            boolean passwordMatch = query.getSingleResult().getPassword().equals(password);
            logger.info("Password check result for user {}: {}", userName, passwordMatch);
            return passwordMatch;
        } catch (Exception e) {
            logger.error("Error checking password for user: {}", userName, e);
            return false;
        }
    }
}
