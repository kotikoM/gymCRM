package com.gym.crm.module.testRepository;

import com.gym.crm.module.repository.TrainerRepo;
import com.gym.crm.module.repository.TrainingRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestTraineeRepo {
    @Autowired
    private TrainingRepo trainingRepo;


    @Autowired
    private TrainerRepo trainerRepo;
    @Test
    void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = (trainingRepo.getTraineeTrainingsByCriteria("ab", null, null, null, 2).toString());
        assertEquals(s, "");
    }

    @Test
    void test1() {
        String s = trainerRepo.getUnassignedTrainers("ab").toString();
        assertEquals(s, "");
    }

}
