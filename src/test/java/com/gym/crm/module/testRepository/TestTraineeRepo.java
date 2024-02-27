package com.gym.crm.module.testRepository;

import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.repository.TraineeRepo;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTraineeRepo {

    @Autowired
    private TraineeRepo traineeRepo;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetAllTrainees() {
        List<Trainee> trainees = sessionFactory.openSession()
                .createQuery("SELECT t FROM Trainee t", Trainee.class)
                .getResultList();

        assertEquals(trainees, traineeRepo.getAllTrainees());
    }

    @Test
    void testGetTraineeById() {
        Integer id = 1;
        Trainee trainee = sessionFactory.openSession()
                .get(Trainee.class, id);
        assertEquals(trainee, traineeRepo.getTraineeById(id));
    }

    @Test
    void testGetTraineeByUserName() {
        String userName = "ab";
        Session session = sessionFactory.openSession();
        TypedQuery<Trainee> query = session.createQuery(
                "SELECT t FROM Trainee t WHERE t.userId = " +
                        "(SELECT u.id FROM User u WHERE u.userName = :userName)",
                Trainee.class);
        query.setParameter("userName", userName);
        Trainee trainee = query.getSingleResult();

        assertEquals(trainee, traineeRepo.getTraineeByUserName(userName));
    }
}
