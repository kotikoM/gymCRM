package com.gym.crm.module.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Trainee {
    private static int traineeIdCounter = 0;

    private int id;
    private Date dateOfBirth;
    private String address;
    private int userId;

    public Trainee(Date dateOfBirth, String address, int userId) {
        this.id = generateId();
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }

    private int generateId() {
        return traineeIdCounter++;
    }
}
