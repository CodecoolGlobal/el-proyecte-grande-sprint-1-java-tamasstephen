package com.example.demo.configuration;

import com.example.demo.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class Config {

    @Bean
    public ArrayList<User> getUsers(){
        return new ArrayList<>();
    }
}
