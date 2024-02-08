package com.gym.crm.module.domain;

import java.security.SecureRandom;

import lombok.Data;

@Data
public class User {
    private static int userIdCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean isActive;

    public User(String firstName, String lastName, boolean isActive) {
        this.id = generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = generateUserName(firstName, lastName);
        this.password = generatePassword();
        this.isActive = isActive;
    }

    private static String generatePassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder password = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    private synchronized int generateId() {
        return userIdCounter++;
    }

    private String generateUserName(String firstName, String lastName) {
        return firstName + "." + lastName;
    }
}
