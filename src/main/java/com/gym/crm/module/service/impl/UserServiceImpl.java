package com.gym.crm.module.service.impl;

import com.gym.crm.module.repository.UserRepo;
import com.gym.crm.module.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (userRepo.checkPassword(userName, oldPassword)) {
            log.info("Updating user password");
            userRepo.updatePassword(userName, newPassword);
        }
    }

    public Boolean checkPassword(String userName, String password) {
        log.info("Checking user password");
        return userRepo.checkPassword(userName, password);
    }
}
