package com.example.demospringsecurity.controllers;

import com.example.demospringsecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String showUserForm() {
        return "user/user";
    }


    @GetMapping("/dba")
    public String showDBAForm() {
        return "dba/dba";
    }



    @GetMapping("/admin")
    public String showAdminForm(User user) {


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return "admin/admin";
    }






}
