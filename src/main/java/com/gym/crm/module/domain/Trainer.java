package com.gym.crm.module.domain;

import lombok.Data;

@Data
public class Trainer {
    private static int trainerIdCounter = 0;
    private long id;
    private TrainingType specialization;
    private long userId;

    public Trainer(TrainingType specialization, long userId) {
        this.id = generateId();
        this.specialization = specialization;
        this.userId = userId;
    }

    private synchronized int generateId() {
        return trainerIdCounter++;
    }
}
