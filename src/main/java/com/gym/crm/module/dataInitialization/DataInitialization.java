package com.gym.crm.module.dataInitialization;

import com.gym.crm.module.dao.*;
import com.gym.crm.module.domain.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DataInitialization {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String dataFile = "data.csv";
    @Autowired
    private TrainerDAO trainerDAO;
    @Autowired
    private TraineeDAO traineeDAO;
    @Autowired
    private TrainingDAO trainingDAO;

    @PostConstruct
    public void initData() {
        try {
            List<String> lines = Files.readAllLines(getFilePath());

            for (String line : lines) {
                String[] tokens = line.split(",");
                switch (tokens[0]) {
                    case "Trainer":
                        initializeTrainer(tokens);
                        break;
                    case "Trainee":
                        initializeTrainee(tokens);
                        break;
                    case "Training":
                        initializeTraining(tokens);
                        break;
                    default:
                        // Handle unknown data type
                        break;
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFilePath() throws IOException {
        return new ClassPathResource(dataFile).getFile().toPath();
    }

    private void initializeTrainer(String[] tokens) {
        TrainingType specialization = TrainingType.valueOf(tokens[1]);
        int userId = Integer.parseInt(tokens[2]);

        trainerDAO.addTrainer(new Trainer(specialization, userId));
    }

    private void initializeTrainee(String[] tokens) throws ParseException {
        Date dateOfBirth = dateFormat.parse(tokens[1]);
        String address = tokens[2];
        int userId = Integer.parseInt(tokens[3]);

        traineeDAO.addTrainee(new Trainee(dateOfBirth, address, userId));
    }

    private void initializeTraining(String[] tokens) throws ParseException {
        int traineeId = Integer.parseInt(tokens[1]);
        int trainerId = Integer.parseInt(tokens[2]);
        String trainingName = tokens[3];
        TrainingType trainingType = TrainingType.valueOf(tokens[4]);
        Date trainingDate = dateFormat.parse(tokens[5]);
        Number trainingDuration = Integer.parseInt(tokens[6]);

        trainingDAO.addTraining(new Training(traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration));
    }
}
