package com.gym.crm.module.service;

import com.gym.crm.module.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepo userRepo;

    public Boolean checkPassword(String userName, String password) {
        return userRepo.checkPassword(userName, password);
    }

    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (userRepo.checkPassword(userName, oldPassword)) {
            userRepo.updatePassword(userName, newPassword);
        }
    }
}
