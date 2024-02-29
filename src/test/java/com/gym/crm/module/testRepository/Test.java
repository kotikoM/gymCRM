package com.gym.crm.module.testRepository;


import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.repository.TraineeRepo;
import com.gym.crm.module.repository.TrainerRepo;
import com.gym.crm.module.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class Test {

    @Autowired
    private TraineeRepo traineeRepo;
    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private UserRepo userRepo;

    @org.junit.jupiter.api.Test
    void test() {
        Trainer trainer = new Trainer(null, 1, 2);
        trainerRepo.createTrainer(trainer);
    }

    @org.junit.jupiter.api.Test
    void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = traineeRepo.createTrainee("jean", "paul", simpleDateFormat.parse("2022-01-01"), "address").toString();
        assertEquals(s, "");
    }
}
