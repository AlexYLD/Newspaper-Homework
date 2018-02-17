package com.company.onlinenewspaper.repository;

import com.company.onlinenewspaper.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface UserRepository {
    User createUser(User user);

    User getByUsername(String username);

    User getById(Integer id);

    List<User> getAll();

    AtomicInteger getAdminCount();
}
