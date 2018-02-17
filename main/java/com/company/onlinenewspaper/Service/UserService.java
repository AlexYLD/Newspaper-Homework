package com.company.onlinenewspaper.Service;

import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.requests.LoginRequest;
import com.company.onlinenewspaper.model.requests.RegisterRequest;

import java.util.List;

public interface UserService {
    User createUser(RegisterRequest request);

    User getByUsernameAndPassword(LoginRequest request);

    List<User> getAll();

    User getById(Integer userId);

    User promote(String username);

    User demote(String username);
}
