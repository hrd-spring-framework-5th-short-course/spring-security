package com.example.demospringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Base64;

@EnableSwagger2
@SpringBootApplication
public class DemoSpringSecurityApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringSecurityApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(passwordEncoder.matches("123123", "$2a$10$9f6Z5d04ErBX6Rpxto1QWeu.pwqfTkbPFrOEVBKVCVRO5c4aZGVS6"));

        System.out.println(passwordEncoder.encode("123123"));
        System.out.println(passwordEncoder.encode("api_user_1234"));

        System.out.println(Base64.getUrlEncoder().encodeToString("user_api:api_user_1234".getBytes()));


        System.out.println(Base64.getUrlDecoder().decode("dXNlcl9hcGk6YXBpX3VzZXJfMTIzNA==".getBytes()).toString().charAt(0));
    }
}

