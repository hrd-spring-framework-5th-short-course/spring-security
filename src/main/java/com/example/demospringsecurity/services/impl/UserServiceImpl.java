package com.example.demospringsecurity.services.impl;

import com.example.demospringsecurity.models.User;
import com.example.demospringsecurity.repositories.UserRepository;
import com.example.demospringsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return this.userRepository.getAllUser();
    }

    @Override
    public boolean save(User user) {
        return this.userRepository.save(user);
    }
}
