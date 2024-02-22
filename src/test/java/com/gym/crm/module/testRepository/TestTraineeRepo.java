package com.gym.crm.module.testRepository;

import com.gym.crm.module.domain.Trainee;
import com.gym.crm.module.repository.TraineeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestTraineeRepo {
    @Autowired
    private TraineeRepo traineeRepo;


    @Test
    void test(){
        List<Trainee> trainees = traineeRepo.getAllTrainees();
        assertEquals(trainees.toString(), "");
    }

    @Test
    void test1(){
        Trainee trainees = traineeRepo.getTraineeByUserName("ab");
//        assertEquals(trainees.toString(), "");
    }

    @Test
    void test2() {
        traineeRepo.updatePassword("ab", "new");
    }

}
