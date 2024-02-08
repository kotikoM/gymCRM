package com.gym.crm.module.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Training {
    private static int trainingIdCounter = 0;

    private int id;
    private int traineeId;
    private int trainerId;
    private String trainingName;
    private TrainingType trainingType;
    private Date trainingDate;
    private Number trainingDuration;

    public Training(int traineeId, int trainerId, String trainingName, TrainingType trainingType,
                    Date trainingDate, Number trainingDuration) {
        this.id = generateId();
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    private int generateId() {
        return trainingIdCounter++;
    }
}
