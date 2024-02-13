package com.gym.crm.module.dao;

import com.gym.crm.module.domain.Trainee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTraineeDAO {
    private TraineeDAO traineeDAO = new TraineeDAO();

    private static final Date date = new Date();
    private static final String address = "address";

    @Test
    void testAddTrainee() {
        Trainee trainee = new Trainee(date, address, 1);
        traineeDAO.addTrainee(trainee);

        assertEquals(trainee, traineeDAO.getTraineeById(trainee.getId()));
    }

    @Test
    void testUpdateTrainee() {
        String updatedAddress = "updated address";
        int updatedUserId = 2;
        Trainee trainee = new Trainee(date, address, 1);
        traineeDAO.addTrainee(trainee);
        Trainee updatedTrainee = new Trainee(date, updatedAddress, updatedUserId);
        updatedTrainee.setId(0);
        traineeDAO.updateTrainee(updatedTrainee);

        assertEquals(updatedAddress, traineeDAO.getTraineeById(trainee.getId()).getAddress());
        assertEquals(updatedUserId, traineeDAO.getTraineeById(trainee.getId()).getUserId());
    }

    @Test
    void testDeleteTrainee() {
        Trainee trainee = new Trainee(date, address, 1);
        traineeDAO.addTrainee(trainee);
        traineeDAO.deleteTrainee(trainee.getId());

        assertNull(traineeDAO.getTraineeById(trainee.getId()));
    }

    @Test
    void testGetTraineeById() {
        Trainee trainee1 = new Trainee(date, address, 1);
        traineeDAO.addTrainee(trainee1);
        Trainee trainee2 = new Trainee(date, address, 2);
        traineeDAO.addTrainee(trainee2);

        assertEquals(trainee1, traineeDAO.getTraineeById(trainee1.getId()));
        assertEquals(trainee2, traineeDAO.getTraineeById(trainee2.getId()));
    }
}
