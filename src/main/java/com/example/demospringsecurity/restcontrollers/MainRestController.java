package com.example.demospringsecurity.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @GetMapping("/test")
    public String testSec(){
        return "Ok success!";
    }

}
