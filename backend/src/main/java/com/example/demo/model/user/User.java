package com.example.demo.model.user;

public class User {
    private long id;
    private CreatorProfile userCreatorProfile;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


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

