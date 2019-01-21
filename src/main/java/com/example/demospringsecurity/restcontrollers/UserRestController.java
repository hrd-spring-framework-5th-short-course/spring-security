package com.example.demospringsecurity.restcontrollers;

import com.example.demospringsecurity.models.User;
import com.example.demospringsecurity.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
//@ApiIgnore

@Api(value = "/users", description = "Operations about users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    @ApiOperation(value = "Get All User Method")
    public ResponseEntity<Map<String, Object>> getUserByUsername() {

        Map<String, Object> response = new HashMap<>();

        List<User> users = this.userService.getAllUser();

        response.put("data", users);
        response.put("message", "Ok good");

        return ResponseEntity.ok(response);
    }


    @PostMapping("")
    public boolean save (@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userService.save(user);
    }
}
