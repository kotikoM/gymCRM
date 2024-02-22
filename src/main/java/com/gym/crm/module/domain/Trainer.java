package com.gym.crm.module.domain;

import lombok.Data;

@Data
public class Trainer {
    private Integer id;
    private TrainingType specialization;
    private Integer userId;

}
