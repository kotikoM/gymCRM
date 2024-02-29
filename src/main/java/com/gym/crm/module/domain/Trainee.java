package com.gym.crm.module.domain;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "address")
    private String address;
    @Column(name = "user_id")
    private Integer userId;

    public Trainee() {
    }

    public Trainee(Date dateOfBirth, String address, Integer userId) {
        this.id = null;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }

    public Trainee(Integer id, Date dateOfBirth, String address, Integer userId) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }
}
