package com.gym.crm.module.testRepository;


import com.gym.crm.module.entity.Trainer;
import com.gym.crm.module.repository.TraineeRepo;
import com.gym.crm.module.repository.TrainerRepo;
import com.gym.crm.module.repository.UserRepo;
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
        String s = traineeRepo.registerTrainee("jean", "paul", simpleDateFormat.parse("2022-01-01"), "address").toString();
        assertEquals(s, "");
    }

    @org.junit.jupiter.api.Test
    void test2() throws ParseException {
        String b1 = userRepo.checkPassword("bob.johnson", "pass123").toString();
        String b2 =  userRepo.checkPassword("bob.johnson", "pass3").toString();
        assertEquals(b1, "true");
        assertEquals(b2, "false");
    }

    }
