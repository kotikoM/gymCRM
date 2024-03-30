package com.gym.crm.module.repository;

import com.gym.crm.module.entity.User;
import com.gym.crm.module.exception.UserCreationException;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;

@Repository
@Slf4j
public class UserRepo {

    @Autowired
    protected SessionFactory sessionFactory;

    public User getUserById(Integer userId) {
        return sessionFactory.openSession()
                .get(User.class, userId);
    }

    public User getUserByUsername(String userName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            return query.getSingleResult();
        }
    }

    public User createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Creating new user: {}", user);
            session.beginTransaction();
            Integer userId = (Integer) session.save(user);
            session.getTransaction().commit();
            log.info("User created successfully with ID: {}", userId);
            return getUserById(userId);
        } catch (RuntimeException e) {
            throw new UserCreationException("User could not be created");
        }
    }

    public User createUser(Integer id, String firstName, String lastName, Boolean isActive) {
        String username = generateUsername(firstName, lastName);
        String password = generatePassword();

        User user = new User(id, firstName, lastName, username, password, isActive);
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

    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void updatePassword(String userName, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Updating password for user: {}", userName);
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            User user = query.getSingleResult();
            user.setPassword(newPassword);
            session.getTransaction().commit();
            log.info("Password updated successfully for user: {}", userName);
        } catch (Exception e) {
            log.error("Error updating password for user: {}", userName, e);
        }
    }

    public void updateIsActive(String userName, boolean isActive) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Updating isActive status for user: {}", userName);
            session.beginTransaction();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);

            User user = query.getSingleResult();
            user.setIsActive(isActive);
            session.getTransaction().commit();
            log.info("isActive status updated successfully for user: {}", userName);
        } catch (Exception e) {
            log.error("Error updating isActive status for user: {}", userName, e);
        }
    }

    public Boolean checkPassword(String userName, String password) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Checking password for user: {}", userName);
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName",
                    User.class);
            query.setParameter("userName", userName);
            boolean passwordMatch = query.getSingleResult().getPassword().equals(password);
            log.info("Password check result for user {}: {}", userName, passwordMatch);
            return passwordMatch;
        } catch (Exception e) {
            log.error("Error checking password for user: {}", userName, e);
            return false;
        }
    }

    private String generateUsername(String firstname, String lastname) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM User u WHERE u.firstname = :firstname AND u.lastname = :lastname",
                    User.class);
            query.setParameter("firstname", firstname);
            query.setParameter("lastname", lastname);
            List<User> usersSameFirstLastName = query.getResultList();

            return firstname +
                    "." +
                    lastname +
                    usersSameFirstLastName.size();
        }
    }

    private String generatePassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder password = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
