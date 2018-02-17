package com.company.onlinenewspaper.Service.impl;

import com.company.onlinenewspaper.Service.UserService;
import com.company.onlinenewspaper.model.Role;
import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.requests.LoginRequest;
import com.company.onlinenewspaper.model.requests.RegisterRequest;
import com.company.onlinenewspaper.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public User createUser(RegisterRequest request) {
        List<User> all = userRepository.getAll();
        User user = User.builder()
                .password(request.getPassword())
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(new ArrayList<>(Arrays.asList(Role.READER)))
                .id(all.size() + 1)
                .build();


        if (all.isEmpty()) {
            user.getRoles().addAll(Arrays.asList(Role.WRITER, Role.ADMIN));
            userRepository.getAdminCount().getAndIncrement();

        }

        return userRepository.createUser(user);
    }

    @Override
    public User getByUsernameAndPassword(LoginRequest request) {
        User user = userRepository.getByUsername(request.getUsername());
        if (user != null && !user.getPassword().equals(request.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(Integer userId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new RuntimeException("No user with such username");
        }
        return user;
    }

    @Override
    public User promote(String username) {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new RuntimeException("No user with such username");
        }
        if (user.getRoles().contains(Role.ADMIN)) {
            throw new RuntimeException("Already admin");
        }
        List<Role> roles = user.getRoles();
        Role role = Role.values()[roles.size()];
        roles.add(role);
        if (roles.contains(Role.ADMIN)) {
            userRepository.getAdminCount().incrementAndGet();
        }
        return user;
    }

    @Override
    public User demote(String username) {
        User user = userRepository.getByUsername(username);

        if (user == null) {
            throw new RuntimeException("No user with such username");
        }

        List<Role> roles = user.getRoles();
        if (roles.contains(Role.ADMIN) && userRepository.getAdminCount().get() == 1) {
            throw new RuntimeException("Last on the field");
        }

        if (roles.size() == 1) {
            throw new RuntimeException("Already on the bottom");
        }

        if (roles.contains(Role.ADMIN)) {
            userRepository.getAdminCount().getAndDecrement();
        }
        roles.remove(roles.size() - 1);

        return user;
    }
}
