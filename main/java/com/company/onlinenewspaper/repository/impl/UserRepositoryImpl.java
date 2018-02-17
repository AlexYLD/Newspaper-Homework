package com.company.onlinenewspaper.repository.impl;

import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static Map<String, User> users = new HashMap<>();
    AtomicInteger adminCount = new AtomicInteger(0);

    @Override
    public User createUser(User user) {
        if (users.putIfAbsent(user.getUsername(), user) != null) {
            throw new RuntimeException("Already exists");
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return users.get(username);
    }

    @Override
    public User getById(Integer id) {
        return users.values().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public AtomicInteger getAdminCount() {
        return adminCount;
    }
}
