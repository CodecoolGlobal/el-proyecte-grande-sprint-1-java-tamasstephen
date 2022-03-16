package com.example.demo.model;

import java.util.Objects;

public class User {
    private long id;
    private Content userContent;
    private String userName;
    private String email;
    private String password;
    private String profileImage;

    // TODO: 16/03/2022 Add constructor after merge

    public boolean isSameCategory(Category category){
        return userContent.getCategory() == category;
    }

    public String getEndpoint(){
        return "";
    }

    public boolean isUserHasSameName(String name){
        return Objects.equals(userName, name);
    }

    public void payUser(){

    }

}
