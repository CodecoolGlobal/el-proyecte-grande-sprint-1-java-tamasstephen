package com.example.demo.model.user;

import java.util.Objects;

public class User {
    private long id;
    private Content userContent;
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void payUser(){
        //TODO: implement payment
    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public void setContent(Content content){
        this.userContent = content;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCreatorProfileAvailable(){
        return userContent != null;
    }
}

