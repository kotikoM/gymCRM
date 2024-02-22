package com.gym.crm.module.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Training {
    private Integer id;
    private Integer traineeId;
    private Integer trainerId;
    private String trainingName;
    private Integer trainingTypeId;
    private Date trainingDate;
    private Number trainingDuration;

}
