package com.gym.crm.module.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "trainee")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Trainee(Date dateOfBirth, String address, Integer userId) {
        this.id = null;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }
}
