package com.gym.crm.module.service;

import com.gym.crm.module.dao.TraineeDAO;
import com.gym.crm.module.domain.Trainee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTraineeService {
    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TraineeDAO traineeDAO;

    @Test
    void testAddTrainee() {
        Date dateOfBirth = new Date();
        String address = "Sample Address";
        int userId = 1;
        traineeService.addTrainee(dateOfBirth, address, userId);
        Trainee addedTrainee = traineeDAO.getTraineeById(0);

        assertNotNull(addedTrainee);
        assertEquals(dateOfBirth.toString(), addedTrainee.getDateOfBirth().toString());
        assertEquals(userId, addedTrainee.getUserId());
    }

    @Test
    void testUpdateTrainee() {
        Date dateOfBirth = new Date();
        String oldAddress = "Old Address";
        String newAddress = "New Address";
        int userId = 1;
        traineeDAO.addTrainee(new Trainee(dateOfBirth, oldAddress, userId));
        Trainee updatedTrainee = new Trainee(dateOfBirth, newAddress, userId);
        updatedTrainee.setId(0);
        traineeService.updateTrainee(updatedTrainee);
        Trainee retrievedTrainee = traineeDAO.getTraineeById(0);

        assertNotNull(retrievedTrainee);
        assertEquals(newAddress, retrievedTrainee.getAddress());
    }

    @Test
    void testDeleteTrainee() {
        Date dateOfBirth = new Date();
        String address = "Sample Address";
        int userId = 1;
        traineeDAO.addTrainee(new Trainee(dateOfBirth, address, userId));
        traineeService.deleteTrainee(0);
        Trainee deletedTrainee = traineeDAO.getTraineeById(0);

        assertNull(deletedTrainee);
    }

    @Test
    void testGetTraineeById() {
        Date dateOfBirth = new Date();
        String address = "Sample Address";
        int userId = 1;
        traineeDAO.addTrainee(new Trainee(dateOfBirth, address, userId));
        Trainee retrievedTrainee = traineeService.getTraineeById(0);

        assertNotNull(retrievedTrainee);
        assertEquals(dateOfBirth.toString(), retrievedTrainee.getDateOfBirth().toString());
        assertEquals(userId, retrievedTrainee.getUserId());
    }
}
