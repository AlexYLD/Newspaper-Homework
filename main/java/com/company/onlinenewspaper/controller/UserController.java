package com.company.onlinenewspaper.controller;

import com.company.onlinenewspaper.Service.UserService;
import com.company.onlinenewspaper.Service.UserSessionService;
import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.UserSession;
import com.company.onlinenewspaper.model.requests.LoginRequest;
import com.company.onlinenewspaper.model.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserSessionService userSessionService;

    @PostMapping("/register")
    public UserSession register(@RequestBody RegisterRequest request) {
        User user = userService.createUser(request);
        return userSessionService.create(UUID.randomUUID().toString(), user);
    }

    @PostMapping("/login")
    public UserSession login(@RequestBody LoginRequest request) {
        User user = userService.getByUsernameAndPassword(request);
        return userSessionService.create(UUID.randomUUID().toString(), user);
    }

    @PutMapping("/logout")
    public void logout(@RequestHeader("Authorization") String sessionId) {
        userSessionService.invalidateSession(sessionId);
    }

    @GetMapping("/get")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    public User getUserInfo(@PathVariable("id") Integer userId) {
        return userService.getById(userId);
    }

    @PutMapping("/promote/{username}")
    public User promote(@PathVariable("username") String username) {
        return userService.promote(username);
    }

    @PutMapping("/demote/{username}")
    public User demote(@PathVariable("username") String username) {
        return userService.demote(username);
    }
}
