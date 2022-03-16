package com.example.demo.model;

import java.util.Objects;

public class User {
    private final long id;
    private Content userContent;
    private String userName;
    private String email;
    private String password;
    private String profileImage;

    public User(long id, Content userContent, String userName, String email, String password, String profileImage) {
        this.id = id;
        this.userContent = userContent;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }

    public boolean isSameCategory(Category category){
        return userContent.getCategory() == category;
    }

    public String getEndpoint(){
        return userContent.getPageLink();
    }

    public boolean isUserHasSameName(String name){
        return Objects.equals(userName, name);
    }

    public void payUser(){

    }

}
