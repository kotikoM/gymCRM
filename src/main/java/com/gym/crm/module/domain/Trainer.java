package com.gym.crm.module.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name =  "trainer")
public class Trainer {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "training_id")
    private Integer trainingId;
    @Column(name = "user_id")
    private Integer userId;
    public Trainer() {

    }

    public Trainer(Integer id, Integer trainingId, Integer userId) {
        this.id = id;
        this.trainingId = trainingId;
        this.userId = userId;
    }
}
