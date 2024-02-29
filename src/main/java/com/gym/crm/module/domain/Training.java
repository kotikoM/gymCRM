package com.gym.crm.module.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trainee_id")
    private Integer traineeId;
    @Column(name = "trainer_id")
    private Integer trainerId;
    @Column(name = "name")
    private String name;
    @Column(name = "training_type_id")
    private Integer trainingTypeId;
    @Column(name = "date")
    private Date date;
    @Column(name = "duration")
    private Integer duration;

    public Training() {
    }

    public Training(Integer id, Integer traineeId, Integer trainerId,
                    String name, Integer trainingTypeId, Date date, Integer duration) {
        this.id = id;
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.trainingTypeId = trainingTypeId;
        this.date = date;
        this.duration = duration;
    }
}
