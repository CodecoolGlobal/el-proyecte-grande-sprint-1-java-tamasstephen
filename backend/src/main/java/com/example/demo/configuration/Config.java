package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class Config {

    @Bean
    public Map<String, Long> getUser(){
        return new HashMap<>();
    }
}
