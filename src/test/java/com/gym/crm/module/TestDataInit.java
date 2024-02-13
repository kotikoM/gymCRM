package com.gym.crm.module;

import com.gym.crm.module.dao.*;
import com.gym.crm.module.dataInitialization.DataInitialization;
import com.gym.crm.module.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestDataInit {
    @Autowired
    private TraineeDAO traineeDAO;
    @Autowired
    private TrainerDAO trainerDAO;
    @Autowired
    private TrainingDAO trainingDAO;

    @Autowired
    private DataInitialization dataInitialization;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    void testTraineeDataInitialization(){
        Trainee trainee1 = traineeDAO.getTraineeById(0);
        Trainee trainee2 = traineeDAO.getTraineeById(1);
        Trainee trainee3 = traineeDAO.getTraineeById(2);

        assertAll(
                () -> assertEquals(dateFormat.parse("2022-01-01"), trainee1.getDateOfBirth()),
                () -> assertEquals("Address1", trainee1.getAddress()),
                () -> assertEquals(101, trainee1.getUserId()),

                () -> assertEquals(dateFormat.parse("2022-01-02"), trainee2.getDateOfBirth()),
                () -> assertEquals("Address2", trainee2.getAddress()),
                () -> assertEquals(102, trainee2.getUserId()),

                () -> assertEquals(dateFormat.parse("2022-01-03"), trainee3.getDateOfBirth()),
                () -> assertEquals("Address3", trainee3.getAddress()),
                () -> assertEquals(103, trainee3.getUserId())
        );
    }
    @Test
    void testTrainerData() {
        Trainer trainer1 = trainerDAO.getTrainerById(0);
        Trainer trainer2 = trainerDAO.getTrainerById(1);
        Trainer trainer3 = trainerDAO.getTrainerById(2);

        assertAll(
                () -> assertEquals(TrainingType.BODYBUILDING, trainer1.getSpecialization()),
                () -> assertEquals(1, trainer1.getUserId()),

                () -> assertEquals(TrainingType.BOXING, trainer2.getSpecialization()),
                () -> assertEquals(2, trainer2.getUserId()),

                () -> assertEquals(TrainingType.CARDIO, trainer3.getSpecialization()),
                () -> assertEquals(3, trainer3.getUserId())
        );
    }
    @Test
    void testTrainingData() {
        Training training1 = trainingDAO.getTrainingById(0);
        Training training2 = trainingDAO.getTrainingById(1);
        Training training3 = trainingDAO.getTrainingById(2);

        assertAll(
                () -> assertEquals(1, training1.getTraineeId()),
                () -> assertEquals(101, training1.getTrainerId()),
                () -> assertEquals("Training 1", training1.getTrainingName()),
                () -> assertEquals(TrainingType.BODYBUILDING, training1.getTrainingType()),
                () -> assertEquals(dateFormat.parse("2022-02-01"), training1.getTrainingDate()),
                () -> assertEquals(60, training1.getTrainingDuration()),

                () -> assertEquals(2, training2.getTraineeId()),
                () -> assertEquals(102, training2.getTrainerId()),
                () -> assertEquals("Training 2", training2.getTrainingName()),
                () -> assertEquals(TrainingType.BOXING, training2.getTrainingType()),
                () -> assertEquals(dateFormat.parse("2022-02-02"), training2.getTrainingDate()),
                () -> assertEquals(45, training2.getTrainingDuration()),

                () -> assertEquals(3, training3.getTraineeId()),
                () -> assertEquals(103, training3.getTrainerId()),
                () -> assertEquals("Training 3", training3.getTrainingName()),
                () -> assertEquals(TrainingType.CARDIO, training3.getTrainingType()),
                () -> assertEquals(dateFormat.parse("2022-02-03"), training3.getTrainingDate()),
                () -> assertEquals(30, training3.getTrainingDuration())
        );
    }
}
