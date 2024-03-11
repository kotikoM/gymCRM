package com.gym.crm.module.service.impl;

import com.gym.crm.module.repository.UserRepo;
import com.gym.crm.module.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);
    @Autowired
    private UserRepo userRepo;

    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (userRepo.checkPassword(userName, oldPassword)) {
            logger.info("Updating user password");
            userRepo.updatePassword(userName, newPassword);
        }
    }

    public Boolean checkPassword(String userName, String password) {
        logger.info("Checking user password");
        return userRepo.checkPassword(userName, password);
    }
}
