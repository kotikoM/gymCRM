package com.gym.crm.module.dao;


import com.gym.crm.module.domain.Trainee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeDAOTest {
    @Autowired
    private TraineeDAO traineeDAO;

    @Test
    void addTrainee() {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        Trainee trainee = new Trainee(Date.from(instant), "address", 1);
        traineeDAO.addTrainee(trainee);

        assert(trainee.equals(traineeDAO.getTraineeById(0)));
    }

}
