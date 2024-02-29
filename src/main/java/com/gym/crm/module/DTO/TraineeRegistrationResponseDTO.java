package com.gym.crm.module.DTO;

import lombok.Data;

@Data
public class TraineeRegistrationResponseDTO {
    private String username;
    private String password;

    public TraineeRegistrationResponseDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
