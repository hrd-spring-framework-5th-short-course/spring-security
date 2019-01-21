package com.example.demospringsecurity.services;

import com.example.demospringsecurity.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    boolean save(User user);
}
