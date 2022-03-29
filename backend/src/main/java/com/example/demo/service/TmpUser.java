package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TmpUser {

private final Map<String, Long> user;

    @Autowired
    public TmpUser(Map<String, Long> user) {
        this.user = user;
    }

    public void setUser(Long id){
        user.put("user", id);
    }

    public Long getUser(){
        return user.get("user");
    }

    public void removeUser(){
        user.remove("user");
    }
}
