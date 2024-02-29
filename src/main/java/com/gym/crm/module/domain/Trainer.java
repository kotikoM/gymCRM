package com.gym.crm.module.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name =  "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "training_type_id")
    private Integer trainingTypeId;
    @Column(name = "user_id")
    private Integer userId;
    public Trainer() {

    }

    public Trainer(Integer id, Integer trainingTypeId, Integer userId) {
        this.id = id;
        this.trainingTypeId = trainingTypeId;
        this.userId = userId;
    }
}
