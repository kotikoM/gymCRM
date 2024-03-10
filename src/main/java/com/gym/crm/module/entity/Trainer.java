package com.gym.crm.module.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name =  "trainer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "training_type_id")
    private Integer trainingTypeId;
    @Column(name = "user_id")
    private Integer userId;
    public Trainer(Integer trainingTypeId, Integer userId) {
        this.id = null;
        this.trainingTypeId = trainingTypeId;
        this.userId = userId;
    }
}
