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

    public boolean isSameCategory(Category category){
        return userContent.getCategory() == category;
    }

    public String getEndpoint(){
        return userContent.getPageLink();
    }

    public boolean hasSameEndpoint(String pageLink){
        return userContent.getPageLink().equals(pageLink);
    }

    public boolean isUserHasSameName(String name){
        return Objects.equals(userContent.getUserName(), name);
    }

    public void payUser(){

    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public String getUserName() {
        return userContent.getUserName();
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
}

