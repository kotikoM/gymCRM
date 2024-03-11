package com.gym.crm.module.service;

public interface UserService {
    void updatePassword(String userName, String oldPassword, String newPassword);
    Boolean checkPassword(String userName, String password);
}
