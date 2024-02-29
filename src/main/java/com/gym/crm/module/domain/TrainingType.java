package com.gym.crm.module.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "training_type")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

}
