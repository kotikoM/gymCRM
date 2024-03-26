package com.gym.crm.module.controller;

import com.gym.crm.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password) {
        Boolean isAuthorized = userService.checkPassword(username, password);
        if (isAuthorized) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changeLogin(
            @RequestParam String username,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        userService.updatePassword(username, oldPassword, newPassword);
        return ResponseEntity.ok().build();
    }
}
