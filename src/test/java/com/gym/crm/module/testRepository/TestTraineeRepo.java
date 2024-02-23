package com.gym.crm.module.testRepository;

import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.domain.Trainer;
import com.gym.crm.module.repository.TrainerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestTraineeRepo {
    @Autowired
    private TrainerRepo trainerRepo;


    @Test
    void test(){
        Trainer trainees = trainerRepo.getTrainerByUserName("ab");
        assertEquals(trainees.toString(), "");
    }

}
