package com.example.demo.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long id;
    private CreatorProfile userCreatorProfile;
    private String email;
    private String password;


    public void payUser(){
        //TODO: implement payment
    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public void setContent(CreatorProfile creatorProfile){
        this.userCreatorProfile = creatorProfile;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean isMatchingEmail(String email){
        return email.equals(this.email);
    }

    public boolean isValidPassword(String password) {
        return password.equals(this.password);
    }

    public long getId() {
        return id;
    }

    public boolean isCreatorProfileAvailable(){
        return userCreatorProfile != null;
    }

    public String getPassword() {
        return password;
    }
}

